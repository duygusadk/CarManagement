package com.example.CarManagement.controller;

import com.example.CarManagement.dto.CreateCarDTO;
import com.example.CarManagement.dto.ResponseCarDTO;
import com.example.CarManagement.dto.UpdateCarDTO;
import com.example.CarManagement.model.Car;
import com.example.CarManagement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping
    public List<ResponseCarDTO> getAllCars(@RequestParam Optional<String> make,
                                           @RequestParam Optional<Long> garageId,
                                           @RequestParam Optional<Integer> fromYear,
                                           @RequestParam Optional<Integer> toYear) {
        return carService.getAllCars(make,garageId,fromYear,toYear);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> getCarById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(carService.getCarById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@Validated@RequestBody CreateCarDTO createCarDTO) {

        return new ResponseEntity<>(carService.createCar(createCarDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @Validated @RequestBody UpdateCarDTO car) {
        return new ResponseEntity<>(carService.updateCar(id, car),HttpStatus.OK) ;
   }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
