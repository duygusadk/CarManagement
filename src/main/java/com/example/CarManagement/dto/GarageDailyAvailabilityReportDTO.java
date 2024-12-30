package com.example.CarManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GarageDailyAvailabilityReportDTO {
    @NonNull
    private String startDate;

    @NonNull
    private String endDate;

    private Long garageId;

    public void setTotalRequests(long totalRequests) {
    }
}
