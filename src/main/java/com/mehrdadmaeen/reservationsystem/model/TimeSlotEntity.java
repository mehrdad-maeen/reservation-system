package com.mehrdadmaeen.reservationsystem.model;


import javax.persistence.*;

@Entity
public class TimeSlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer  timeSlot;
    private String  reservationTime;





    public TimeSlotEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }
}
