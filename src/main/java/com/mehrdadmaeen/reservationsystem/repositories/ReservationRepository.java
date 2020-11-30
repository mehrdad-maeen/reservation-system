package com.mehrdadmaeen.reservationsystem.repositories;


import com.mehrdadmaeen.reservationsystem.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    <T> List<T> findAllByReservationDate(LocalDate date, Class<T> classz);
    <T> Optional<T> findByReservationDateAndTable_IdAndReservationTime_Id(LocalDate date, Long tableId,Integer timeId, Class<T> classz);
    <T> Optional<T> findById(Long id, Class<T> classz);
}



