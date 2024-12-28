package com.example.CarManagement.service;

import com.example.CarManagement.model.Car;
import com.example.CarManagement.model.Garage;
import com.example.CarManagement.repository.CarRepository;
import com.example.CarManagement.repository.GarageRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private GarageRepository garageRepository;

    public List<Car> getAllCars(Optional<String> make,
                                Optional<Long> garageId,
                                Optional<Integer> fromYear,
                                Optional<Integer> toYear) {
        if(make.isPresent()){
            return carRepository.findByMake(make.get());
        } else if (garageId.isPresent()) {
            return carRepository.findByGarages_Id(garageId.get());
        }else if(fromYear.isPresent() && toYear.isPresent()) {
            return carRepository.findByProductionYearBetween(fromYear.get(), toYear.get());
        }
        return carRepository.findAll();

    }

    public Car getCarById(Long id){

        return  carRepository.findById(id).orElseThrow();

    }

    public Car createCar(Car car) {


        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car car) {

        Car updatedCar=carRepository.findById(id).orElseThrow();
        if (car.getGarages() != null) {
            List<Garage> garages = garageRepository.findAllById(
                    car.getGarages().stream().map(Garage::getId).toList()
            );
            updatedCar.setGarages(garages);
        }
        updatedCar.setMake(car.getMake());
        updatedCar.setModel(car.getModel());
        updatedCar.setLicensePlate(car.getLicensePlate());
        updatedCar.setProductionYear(car.getProductionYear());
        updatedCar.setId(car.getId());
        return  carRepository.save(updatedCar);
    }

    public void deleteCar(Long id) {
      carRepository.deleteById(id);
    }
}
