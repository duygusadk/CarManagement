package com.example.CarManagement.repository;

import com.example.CarManagement.model.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest,Long> {
    List<MaintenanceRequest> findByCarId(Long carId);
    List<MaintenanceRequest> findByGarageId(Long serviceCenterId);
    List<MaintenanceRequest> findByScheduledDateBetween(String startDate, String endDate);

    List<MaintenanceRequest> findByScheduledDateBetweenAndGarageId(String startDate, String endDate, Long garageId);

    List<MaintenanceRequest> findByScheduledDateAndGarageId(String string, Long garageId);
}
