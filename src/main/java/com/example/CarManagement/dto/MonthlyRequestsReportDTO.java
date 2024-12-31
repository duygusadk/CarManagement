package com.example.CarManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;


@NoArgsConstructor
public class MonthlyRequestsReportDTO {
    @NonNull
    private String yearMonth;

    private int requests;


    public MonthlyRequestsReportDTO(@NonNull String yearMonth, int requests) {
        this.yearMonth = yearMonth;
        this.requests = requests;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }
}


