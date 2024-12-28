package com.example.CarManagement.service;

import com.example.CarManagement.model.Car;
import com.example.CarManagement.model.Garage;
import com.example.CarManagement.model.MaintenanceRequest;
import com.example.CarManagement.repository.CarRepository;
import com.example.CarManagement.repository.GarageRepository;
import com.example.CarManagement.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceRequestService {

    @Autowired
    private MaintenanceRequestRepository maintenanceRequestRepository;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private GarageRepository garageRepository;

    public List<MaintenanceRequest> getAllRequests(Optional<Long> carId, Optional<Long> garageId, Optional<String> startDate, Optional<String> endDate) {
        if (carId.isPresent()) {
            return maintenanceRequestRepository.findByCarId(carId.get());
        } else if (garageId.isPresent()) {
            return maintenanceRequestRepository.findByGarageId(garageId.get());
        } else if (startDate.isPresent() && endDate.isPresent()) {
            return maintenanceRequestRepository.findByScheduledDateBetween(startDate.get(), endDate.get());
        }
        return maintenanceRequestRepository.findAll();
    }
    public MaintenanceRequest getMaintenanceRequestById(Long id) {
        return maintenanceRequestRepository.findById(id).orElseThrow();
    }
    public MaintenanceRequest createRequest(MaintenanceRequest request) {
        Garage garage = garageRepository.findById(request.getGarage().getId()).orElseThrow();
        Car car = carRepository.findById(request.getCar().getId()).orElseThrow();
        long bookedRequests = maintenanceRequestRepository.countByGarageIdAndScheduledDate(garage.getId(), request.getScheduledDate());

        if (bookedRequests >= garage.getCapacity()) {
            throw new IllegalArgumentException("No available slots in the garage for the selected date.");
        }
        request.setGarage(garage);
        request.setCar(car);
        return maintenanceRequestRepository.save(request);

    }

    public MaintenanceRequest updateRequest(Long id, MaintenanceRequest requestDetails) {
        MaintenanceRequest request = maintenanceRequestRepository.findById(id)
                .orElseThrow();

        request.setScheduledDate(requestDetails.getScheduledDate());
        request.setCar(requestDetails.getCar());
        request.setGarage(requestDetails.getGarage());
        request.setServiceType(requestDetails.getServiceType());
        return maintenanceRequestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        MaintenanceRequest request = maintenanceRequestRepository.findById(id)
                .orElseThrow();
        maintenanceRequestRepository.delete(request);
    }

}
