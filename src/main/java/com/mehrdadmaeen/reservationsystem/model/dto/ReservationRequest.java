package com.mehrdadmaeen.reservationsystem.model.dto;

import java.time.LocalDate;

public class ReservationRequest {

    private String name;
    private String contact;
    private LocalDate reservationDate;
    private String reservationTime;
    private String tableName;

    public ReservationRequest(String name, String contact, LocalDate reservationDate, String reservationTime, String tableName) {
        this.name = name;
        this.contact = contact;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
