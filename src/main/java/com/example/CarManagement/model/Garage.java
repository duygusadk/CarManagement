package com.example.CarManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String city;
    private int capacity;
    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL)
    private List<MaintenanceRequest> maintenanceRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<MaintenanceRequest> getMaintenanceRequests() {
        return maintenanceRequests;
    }

    public void setMaintenanceRequests(List<MaintenanceRequest> maintenanceRequests) {
        this.maintenanceRequests = maintenanceRequests;
    }
}
