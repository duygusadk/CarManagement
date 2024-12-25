package com.example.CarManagement.repository;

import com.example.CarManagement.model.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest,Long> {
    List<MaintenanceRequest> findByCarId(Long carId);
    List<MaintenanceRequest> findByServiceCenterId(Long serviceCenterId);
    List<MaintenanceRequest> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
