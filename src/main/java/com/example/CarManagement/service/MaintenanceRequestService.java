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
    public MaintenanceRequest getMaintenanceRequestById(Long id) {
        return maintenanceRequestRepository.findById(id).orElseThrow();
    }
    public MaintenanceRequest createRequest(CreateMaintenanceDTO request) {
        Garage garage = garageRepository.findById(request.getGarageId()).orElseThrow();
        Car car = carRepository.findById(request.getCarId()).orElseThrow();

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setCar(car);
        maintenanceRequest.setGarage(garage);
        maintenanceRequest.setServiceType(request.getServiceType());
        maintenanceRequest.setScheduledDate(request.getScheduledDate());
        return maintenanceRequestRepository.save(maintenanceRequest);

    }

    public MaintenanceRequest updateRequest(Long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
        MaintenanceRequest request = maintenanceRequestRepository.findById(id)
                .orElseThrow();

        Car car = carRepository.findById(updateMaintenanceDTO.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Car with ID " + updateMaintenanceDTO.getCarId() + " not found"));

        Garage garage = garageRepository.findById(updateMaintenanceDTO.getGarageId())
                .orElseThrow(() -> new IllegalArgumentException("Garage with ID " + updateMaintenanceDTO.getGarageId() + " not found"));
        request.setGarage(garage);
        request.setServiceType(updateMaintenanceDTO.getServiceType());
        request.setScheduledDate(updateMaintenanceDTO.getScheduledDate());
        return maintenanceRequestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        MaintenanceRequest request = maintenanceRequestRepository.findById(id)
                .orElseThrow();
        maintenanceRequestRepository.delete(request);
    }

    public List<MonthlyRequestsReportDTO> getMonthlyReport(Optional<Long> garageId, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<MonthlyRequestsReportDTO> report = new ArrayList<>();


        return report;
    }

}
