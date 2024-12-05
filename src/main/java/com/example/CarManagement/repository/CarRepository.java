package com.example.CarManagement.repository;

import com.example.CarManagement.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByBrand(String brand);
    List<Car> findByYearBetween(int startYear, int endYear);
}
