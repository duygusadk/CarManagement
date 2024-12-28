package com.example.CarManagement.repository;

import com.example.CarManagement.model.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest,Long> {
    List<MaintenanceRequest> findByCarId(Long carId);
    List<MaintenanceRequest> findByGarageId(Long serviceCenterId);
    List<MaintenanceRequest> findByScheduledDateBetween(String startDate, String endDate);
    @Query("SELECT COUNT(m) FROM MaintenanceRequest m WHERE m.garage.id = :garageId AND m.scheduledDate = :scheduledDate")
    long countByGarageIdAndScheduledDate(@Param("garageId") Long garageId, @Param("scheduledDate") String scheduledDate);


}
