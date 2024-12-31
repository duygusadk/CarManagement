package com.example.CarManagement.controller;

import com.example.CarManagement.dto.CreateGarageDTO;
import com.example.CarManagement.dto.GarageDailyAvailabilityReportDTO;
import com.example.CarManagement.dto.ResponseGarageDTO;
import com.example.CarManagement.dto.UpdateGarageDTO;
import com.example.CarManagement.model.Garage;
import com.example.CarManagement.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/garages")
public class GarageController {
    @Autowired
    private GarageService garageService;

    @GetMapping
    public List<ResponseGarageDTO> getAllGarages(@RequestParam Optional<String> city) {
            return garageService.getAllGarage(city);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseGarageDTO> getGarageById(@PathVariable Long id) {
        return new ResponseEntity<>(garageService.getGarageById(id),HttpStatus.OK) ;
    }
    @GetMapping("/dailyAvailabilityReport")
    public List<GarageDailyAvailabilityReportDTO> getDaily(@RequestParam Long garageId,
                                                           @RequestParam String startDate,
                                                           @RequestParam String endDate) {
        return garageService.getGarageDailyAvailabilityReport(garageId,startDate,endDate);

    }

    @PostMapping
    public ResponseEntity<Garage> createGarage(@RequestBody CreateGarageDTO garage) {
        try {

            return new ResponseEntity<>( garageService.createGarage(garage), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Garage> updateGarage(@PathVariable Long id,@Validated@RequestBody UpdateGarageDTO garage) {

        Garage updatedGarage = garageService.updateGarage(id, garage);
        return ResponseEntity.ok(updatedGarage);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGarage(@PathVariable Long id) {

        try {
            garageService.deleteGarage(id);
            return new ResponseEntity<>("The garage is deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("The garage is not deleted", HttpStatus.BAD_REQUEST);
        }
    }
}
