package com.example.CarManagement.controller;

import com.example.CarManagement.model.Car;
import com.example.CarManagement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

   // @PutMapping("/{id}")
   // public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
  //      return carService.updateCar(id, car);
  //  }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
