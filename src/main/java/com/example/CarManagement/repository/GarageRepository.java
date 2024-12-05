package com.example.CarManagement.repository;

import com.example.CarManagement.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GarageRepository extends JpaRepository<Garage,Long> {
    List<Garage> findByCity(String city);

}
