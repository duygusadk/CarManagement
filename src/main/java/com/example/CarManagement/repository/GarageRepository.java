package com.example.CarManagement.repository;

import com.example.CarManagement.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage,Long> {
    List<Garage> findByCity(String city);

}
