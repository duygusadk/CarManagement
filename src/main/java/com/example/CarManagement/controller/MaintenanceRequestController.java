package com.example.CarManagement.controller;

import com.example.CarManagement.dto.CreateMaintenanceDTO;
import com.example.CarManagement.dto.MonthlyRequestsReportDTO;
import com.example.CarManagement.dto.ResponseMaintenanceDTO;
import com.example.CarManagement.dto.UpdateMaintenanceDTO;
import com.example.CarManagement.model.Garage;
import com.example.CarManagement.model.MaintenanceRequest;
import com.example.CarManagement.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceRequestController {
    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    @GetMapping
    public List<ResponseMaintenanceDTO> getAllRequests(@RequestParam Optional<Long> carId,
                                                       @RequestParam Optional<Long> garageId,
                                                       @RequestParam Optional<String> startDate,
                                                       @RequestParam Optional<String> endDate) {
        return maintenanceRequestService.getAllRequests(carId, garageId, startDate, endDate);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceRequest> getRequestById(@PathVariable Long id) {
    return  new ResponseEntity<>(maintenanceRequestService.getMaintenanceRequestById(id), HttpStatus.OK);
    }
    @GetMapping("/monthlyRequestsReport")
    public List<MonthlyRequestsReportDTO> getMonthlyReport(
            @RequestParam Optional<Long> garageId,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        return maintenanceRequestService.getMonthlyReport(garageId, startDate, endDate);
    }
    @PostMapping
    public ResponseEntity<MaintenanceRequest> createRequest(@RequestBody CreateMaintenanceDTO maintenanceRequest) {
        try {

            return new ResponseEntity<>( maintenanceRequestService.createRequest(maintenanceRequest), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceRequest> updateRequest(@PathVariable Long id, @RequestBody UpdateMaintenanceDTO maintenanceRequest) {
        try {

            return new ResponseEntity<>(maintenanceRequestService.updateRequest(id, maintenanceRequest), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {

        try {
            maintenanceRequestService.deleteRequest(id);
            return new ResponseEntity<>("The request is deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("The request is not deleted", HttpStatus.BAD_REQUEST);
        }
    }
}
