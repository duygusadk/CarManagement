package com.example.CarManagement.service;

import com.example.CarManagement.model.Car;
import com.example.CarManagement.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Data
@AllArgsConstructor
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

  /*  public Car updateCar(Long id, Car car) {
        if(carRepository.findById(id).isEmpty()){
            throw new NoSuchElementException();
        }
        Car updatedCar=carRepository.findById(id).get();
        updatedCar.setGarages(car.getGarages());
        updatedCar.setMake(car.getMake());
        updatedCar.setModel(car.getModel());
        updatedCar.setLicensePlate(car.getLicensePlate());
        updatedCar.setProductionYear(car.getProductionYear());
        updatedCar.setId(car.getId());
        return  carRepository.save(updatedCar);
    }*/

    public void deleteCar(Long id) {
      carRepository.deleteById(id);
    }
}
