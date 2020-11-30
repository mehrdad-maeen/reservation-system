package com.mehrdadmaeen.reservationsystem.repositories;

import com.mehrdadmaeen.reservationsystem.model.TimeSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlotEntity,Integer> {
    <T> Optional<T> findByReservationTime(String reservationTime, Class<T> classz);
    <T> Optional<T> findByTimeSlot(Integer timeSlot, Class<T> classz);
}
