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

import java.time.LocalDate;
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
    public ResponseMaintenanceDTO getRequestById(@PathVariable Long id) {
        return maintenanceRequestService.getMaintenanceRequestById(id);
    }

    @GetMapping("/monthlyRequestsReport")
    public List<MonthlyRequestsReportDTO> getMonthlyReport(
            @RequestParam Long garageId,
            @RequestParam String startMonth,
            @RequestParam String endMonth) {

        return maintenanceRequestService.getMonthlyReport(garageId, startMonth, endMonth);
    }

    @PostMapping
    public ResponseMaintenanceDTO createRequest(@RequestBody CreateMaintenanceDTO maintenanceRequest) {

        return maintenanceRequestService.createRequest(maintenanceRequest);
    }

    @PutMapping("/{id}")
    public ResponseMaintenanceDTO updateRequest(@PathVariable Long id, @RequestBody UpdateMaintenanceDTO maintenanceRequest) {
        return maintenanceRequestService.updateRequest(id, maintenanceRequest);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {

        try {
            maintenanceRequestService.deleteRequest(id);
            return new ResponseEntity<>("The request is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The request is not deleted", HttpStatus.BAD_REQUEST);
        }
    }
}
