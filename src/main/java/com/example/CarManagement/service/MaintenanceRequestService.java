package com.example.CarManagement.service;

import com.example.CarManagement.dto.*;
import com.example.CarManagement.model.Car;
import com.example.CarManagement.model.Garage;
import com.example.CarManagement.model.MaintenanceRequest;
import com.example.CarManagement.repository.CarRepository;
import com.example.CarManagement.repository.GarageRepository;
import com.example.CarManagement.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaintenanceRequestService {

    @Autowired
    private MaintenanceRequestRepository maintenanceRequestRepository;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private GarageRepository garageRepository;

    public List<ResponseMaintenanceDTO> getAllRequests(Optional<Long> carId,
                                                   Optional<Long> garageId,
                                                   Optional<String> startDate,
                                                   Optional<String> endDate) {

        List<MaintenanceRequest> maintenanceRequests;
        if (carId.isPresent()) {
            maintenanceRequests= maintenanceRequestRepository.findByCarId(carId.get());
        } else if (garageId.isPresent()) {
            maintenanceRequests= maintenanceRequestRepository.findByGarageId(garageId.get());
        } else if (startDate.isPresent() && endDate.isPresent()) {
            maintenanceRequests= maintenanceRequestRepository.findByScheduledDateBetween(startDate.get(), endDate.get());
        }else {
            maintenanceRequests = maintenanceRequestRepository.findAll();
        }
        return maintenanceRequests.stream()
                .map(request -> new ResponseMaintenanceDTO(
                        request.getId(),
                        request.getCar().getId(),
                        request.getCar().getMake() + " " + request.getCar().getModel(),
                        request.getServiceType(),
                        request.getScheduledDate(),
                        request.getGarage().getId(),
                        request.getGarage().getName()
                ))
                .collect(Collectors.toList());
    }
    public ResponseMaintenanceDTO getMaintenanceRequestById(Long id) {
        MaintenanceRequest request=maintenanceRequestRepository.findById(id).orElseThrow();
        return new ResponseMaintenanceDTO( request.getId(),
                request.getCar().getId(),
                request.getCar().getMake() + " " + request.getCar().getModel(),
                request.getServiceType(),
                request.getScheduledDate(),
                request.getGarage().getId(),
                request.getGarage().getName());
    }
    public ResponseMaintenanceDTO createRequest(CreateMaintenanceDTO request) {
        Garage garage = garageRepository.findById(request.getGarageId()).orElseThrow();
        Car car = carRepository.findById(request.getCarId()).orElseThrow();

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setCar(car);
        maintenanceRequest.setGarage(garage);
        maintenanceRequest.setServiceType(request.getServiceType());
        maintenanceRequest.setScheduledDate(request.getScheduledDate());

        maintenanceRequest=maintenanceRequestRepository.save(maintenanceRequest);
        return new ResponseMaintenanceDTO(maintenanceRequest.getId(),
                maintenanceRequest.getCar().getId(),
                maintenanceRequest.getCar().getMake() + " " + maintenanceRequest.getCar().getModel(),
                maintenanceRequest.getServiceType(),
                maintenanceRequest.getScheduledDate(),
                maintenanceRequest.getGarage().getId(),
                maintenanceRequest.getGarage().getName());

    }

    public ResponseMaintenanceDTO updateRequest(Long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
        MaintenanceRequest request = maintenanceRequestRepository.findById(id)
                .orElseThrow();

        Car car = carRepository.findById(updateMaintenanceDTO.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Car with ID " + updateMaintenanceDTO.getCarId() + " not found"));

        Garage garage = garageRepository.findById(updateMaintenanceDTO.getGarageId())
                .orElseThrow(() -> new IllegalArgumentException("Garage with ID " + updateMaintenanceDTO.getGarageId() + " not found"));
        request.setGarage(garage);
        request.setCar(car);
        request.setServiceType(updateMaintenanceDTO.getServiceType());
        request.setScheduledDate(updateMaintenanceDTO.getScheduledDate());
        request=maintenanceRequestRepository.save(request);
        return new ResponseMaintenanceDTO(request.getId(),
                request.getCar().getId(),
                request.getCar().getMake() + " " + request.getCar().getModel(),
                request.getServiceType(),
                request.getScheduledDate(),
                request.getGarage().getId(),
                request.getGarage().getName());
    }

    public void deleteRequest(Long id) {
        MaintenanceRequest request = maintenanceRequestRepository.findById(id)
                .orElseThrow();
        maintenanceRequestRepository.delete(request);
    }

    public List<MonthlyRequestsReportDTO> getMonthlyReport(Long garageId, String start, String end ) {

        List<MonthlyRequestsReportDTO> report = new ArrayList<>();

        YearMonth startDate=YearMonth.parse(start);
        YearMonth endDate=YearMonth.parse(end);
        YearMonth currentMonth = startDate;


        while (!currentMonth.isAfter(endDate)) {
            LocalDate monthStartDate = currentMonth.atDay(1);
            LocalDate monthEndDate = currentMonth.atEndOfMonth();

            List<MaintenanceRequest> monthlyRequests = maintenanceRequestRepository.findByScheduledDateBetweenAndGarageId(
                    monthStartDate.toString(),
                    monthEndDate.toString(),
                    garageId
            );

            int totalRequests = monthlyRequests.size();

            MonthlyRequestsReportDTO dto = new MonthlyRequestsReportDTO(currentMonth.toString(), totalRequests );
            report.add(dto);
            currentMonth = currentMonth.plusMonths(1);

        }
        return report;
    }

}
