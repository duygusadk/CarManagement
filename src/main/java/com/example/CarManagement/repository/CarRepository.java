package com.example.CarManagement.repository;

import com.example.CarManagement.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByMake(String make);
    List<Car> findByProductionYearBetween(int startYear, int endYear);

    List<Car> findByGarages_Id(Long garageId);
}
