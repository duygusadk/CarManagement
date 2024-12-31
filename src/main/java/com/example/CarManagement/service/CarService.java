package com.example.CarManagement.service;

import com.example.CarManagement.dto.CreateCarDTO;
import com.example.CarManagement.dto.ResponseCarDTO;
import com.example.CarManagement.dto.UpdateCarDTO;
import com.example.CarManagement.model.Car;
import com.example.CarManagement.model.Garage;
import com.example.CarManagement.repository.CarRepository;
import com.example.CarManagement.repository.GarageRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private GarageRepository garageRepository;

    public List<ResponseCarDTO> getAllCars(Optional<String> make,
                                           Optional<Long> garageId,
                                           Optional<Integer> fromYear,
                                           Optional<Integer> toYear) {

        List<Car>cars;
        if(make.isPresent()){
            cars= carRepository.findByMake(make.get());
        } else if (garageId.isPresent()) {
            cars= carRepository.findByGarages_Id(garageId.get());
        }else if(fromYear.isPresent() && toYear.isPresent()) {
            cars=  carRepository.findByProductionYearBetween(fromYear.get(), toYear.get());
        }else{
            cars=  carRepository.findAll();
        }

        return cars.stream()
                .map(car -> new ResponseCarDTO(
                        car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getProductionYear(),
                        car.getLicensePlate(),
                        car.getGarages()
                ))
                .collect(Collectors.toList());
    }

    public ResponseCarDTO getCarById(Long id){
        Car car=carRepository.findById(id).orElseThrow();
        return new ResponseCarDTO(
                car.getId(),
                car.getMake(),
                car.getModel(),
                car.getProductionYear(),
                car.getLicensePlate(),
                car.getGarages()
        );

    }

    public Car createCar(CreateCarDTO createCarDTO) {


        Car car = new Car();
        car.setMake(createCarDTO.getMake());
        car.setModel(createCarDTO.getModel());
        car.setLicensePlate(createCarDTO.getLicensePlate());
        car.setProductionYear(createCarDTO.getProductionYear());

        List<Garage> garages = new ArrayList<>();
        if (createCarDTO.getGarageIds() != null) {
            for (Long garageId : createCarDTO.getGarageIds()) {
                Garage garage = garageRepository.findById(garageId).orElseThrow(() -> new IllegalArgumentException("Garage not found"));
                garages.add(garage);
            }
        }
        car.setGarages(garages);

        return carRepository.save(car);
    }

    public Car updateCar(Long id, UpdateCarDTO updateCarDTO) {

        Car updatedCar=carRepository.findById(id).orElseThrow();
        if (updateCarDTO.getGarageIds() != null) {
            List<Garage> garages = updateCarDTO.getGarageIds()
                    .stream()
                    .map(garageId -> garageRepository.findById(garageId)
                            .orElseThrow(() -> new IllegalArgumentException("Garage with ID " + garageId + " not found")))
                    .collect(Collectors.toList());
            updatedCar.setGarages(garages);
        }
        updatedCar.setMake(updateCarDTO.getMake());
        updatedCar.setModel(updateCarDTO.getModel());
        updatedCar.setLicensePlate(updateCarDTO.getLicensePlate());
        updatedCar.setProductionYear(updateCarDTO.getProductionYear());

        return  carRepository.save(updatedCar);
    }

    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow();
        car.getGarages().clear();
      carRepository.deleteById(id);
    }
}
