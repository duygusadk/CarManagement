package com.example.CarManagement.repository;

import com.example.CarManagement.model.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest,Long> {
    List<MaintenanceRequest> findByCarId(Long carId);
    List<MaintenanceRequest> findByServiceCenterId(Long serviceCenterId);
    List<MaintenanceRequest> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
