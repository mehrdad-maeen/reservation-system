package com.mehrdadmaeen.reservationsystem.model;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "RESERVATION_ENTITY")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    public String name;

    private String contact;

    private LocalDate reservationDate;

    @OneToOne()
    @JoinColumn(name = "reservation_time_id")
    private TimeSlotEntity reservationTime;

    @OneToOne()
    @JoinColumn(name = "table_id")
    private TableEntity table;

    public ReservationEntity() {

    }

    public Long getId() {
        return id;
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

    public TimeSlotEntity getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(TimeSlotEntity reservationTime) {
        this.reservationTime = reservationTime;
    }

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
    }
}
