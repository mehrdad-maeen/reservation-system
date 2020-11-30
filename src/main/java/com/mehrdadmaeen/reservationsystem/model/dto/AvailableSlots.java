package com.mehrdadmaeen.reservationsystem.model.dto;

import java.time.LocalDate;

public class AvailableSlots {
    private String tableName;
    private LocalDate availableDate;
    private String availableTime;

    public AvailableSlots(String tableName, LocalDate availableDate, String availableTime) {
        this.tableName = tableName;
        this.availableDate = availableDate;
        this.availableTime = availableTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }
}
