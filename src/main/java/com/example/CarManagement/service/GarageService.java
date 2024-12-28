package com.example.CarManagement.service;

import com.example.CarManagement.model.Garage;
import com.example.CarManagement.repository.GarageRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service

public class GarageService {
    @Autowired
    private GarageRepository garageRepository;


    public List<Garage> getAllGarage(Optional<String> city) {
        if(city.isPresent()){
            return garageRepository.findByCity(city.get());
        }
        return garageRepository.findAll();
    }
    public Garage getGarageById(Long id) {
        return garageRepository.findById(id).orElseThrow();
    }

    public Garage createGarage(Garage garage) {
       return garageRepository.save(garage);
    }

    public Garage updateGarage(Long id, Garage garage) {

      Garage updatedGarage=garageRepository.findById(id).orElseThrow();

      updatedGarage.setId(garage.getId());
      updatedGarage.setCity(garage.getCity());
      updatedGarage.setName(garage.getName());
      updatedGarage.setLocation(garage.getLocation());
      updatedGarage.setCapacity(garage.getCapacity());

      return garageRepository.save(updatedGarage);
    }

    public void deleteGarage(Long id) {
        garageRepository.deleteById(id);
    }
}
