package com.example.CarManagement.dto;

public class CreateMaintenanceDTO {
    private Long garageId;
    private Long carId;
    private String serviceType;
    private String scheduledDate;

    public CreateMaintenanceDTO(Long garageId, Long carId, String serviceType, String scheduledDate) {
        this.garageId = garageId;
        this.carId = carId;
        this.serviceType = serviceType;
        this.scheduledDate = scheduledDate;
    }

    public Long getGarageId() {
        return garageId;
    }

    public void setGarageId(Long garageId) {
        this.garageId = garageId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
