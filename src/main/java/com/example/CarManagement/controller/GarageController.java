package com.example.CarManagement.controller;

import com.example.CarManagement.model.Garage;
import com.example.CarManagement.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GarageController {
    @Autowired
    private GarageService garageService;

    @GetMapping
    public List<Garage> getAllGarages() {

        return garageService.getAllGarage();
    }

    @PostMapping
    public ResponseEntity<String> createGarage(@RequestBody Garage garage) {
        try {
            garageService.createGarage(garage);
            return new ResponseEntity<>("The garage is created", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("The garage is not created", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGarage(@PathVariable Long id, @RequestBody Garage garage) {
        try {
           // garageService.updateGarage(id, garage);
            return new ResponseEntity<>("The garage is updated", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("The garage is not updated", HttpStatus.BAD_REQUEST);
        }

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
