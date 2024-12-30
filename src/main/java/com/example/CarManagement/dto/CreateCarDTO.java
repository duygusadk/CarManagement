package com.example.CarManagement.dto;

import com.example.CarManagement.model.Garage;

import java.util.List;

public class CreateCarDTO {

    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;

    private List<Long> garageIds;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<Long> getGarageIds() {
        return garageIds;
    }

    public void setGarageIds(List<Long> garageIds) {
        this.garageIds = garageIds;
    }
}
