package com.example.CarManagement.service;

import com.example.CarManagement.dto.CreateGarageDTO;
import com.example.CarManagement.dto.GarageDailyAvailabilityReportDTO;
import com.example.CarManagement.dto.ResponseGarageDTO;
import com.example.CarManagement.dto.UpdateGarageDTO;
import com.example.CarManagement.model.Garage;
import com.example.CarManagement.repository.GarageRepository;
import com.example.CarManagement.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class GarageService {
    @Autowired
    private GarageRepository garageRepository;

    private MaintenanceRequestRepository maintenanceRequestRepository;


    public List<ResponseGarageDTO> getAllGarage(Optional<String> city) {
        List<Garage> garages;
        if(city.isPresent()){
            garages= garageRepository.findByCity(city.get());
        }else {
            garages = garageRepository.findAll();
        }
        return garages.stream().map(garage->new ResponseGarageDTO(
                garage.getId(),
                garage.getName(),
                garage.getLocation(),
                garage.getCity(),
                garage.getCapacity())).collect(Collectors.toList());
    }
    public ResponseGarageDTO getGarageById(Long id) {
        Garage garage=garageRepository.findById(id).orElseThrow();
       return new ResponseGarageDTO(
               garage.getId(),
               garage.getName(),
               garage.getLocation(),
               garage.getCity(),
               garage.getCapacity());

    }

    public Garage createGarage(CreateGarageDTO createGarageDTO) {
        Garage garage = new Garage();
        garage.setName(createGarageDTO.getName());
        garage.setLocation(createGarageDTO.getLocation());
        garage.setCity(createGarageDTO.getCity());
        garage.setCapacity(createGarageDTO.getCapacity());

       return garageRepository.save(garage);
    }

    public Garage updateGarage(Long id, UpdateGarageDTO garage) {

      Garage updatedGarage=garageRepository.findById(id).orElseThrow();

      updatedGarage.setCity(garage.getCity());
      updatedGarage.setName(garage.getName());
      updatedGarage.setLocation(garage.getLocation());
      updatedGarage.setCapacity(garage.getCapacity());

      return garageRepository.save(updatedGarage);
    }

    public void deleteGarage(Long id) {
        garageRepository.deleteById(id);
    }


    public GarageDailyAvailabilityReportDTO garageReport(Long garageId, String startDate, String endDate) {

      return null;
    }

}
