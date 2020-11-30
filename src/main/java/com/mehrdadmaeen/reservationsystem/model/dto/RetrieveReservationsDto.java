package com.mehrdadmaeen.reservationsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mehrdadmaeen.reservationsystem.model.ReservationEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;


@Projection(types = ReservationEntity.class)
@JsonPropertyOrder({ "id","name", "contact", "reservationDate","reservationTime","tableName" })
public interface RetrieveReservationsDto {


    @Value("#{target.table.tableName}")
    String getTableName();

    String getName();

    Long getId();

    @Value("#{target.reservationTime.reservationTime}")
    String getReservationTime();

    String getContact();

    LocalDate getReservationDate();
}








