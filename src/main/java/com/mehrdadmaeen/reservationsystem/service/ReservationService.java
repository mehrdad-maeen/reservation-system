package com.mehrdadmaeen.reservationsystem.service;


import com.mehrdadmaeen.reservationsystem.model.ReservationEntity;
import com.mehrdadmaeen.reservationsystem.model.TableEntity;
import com.mehrdadmaeen.reservationsystem.model.TimeSlotEntity;
import com.mehrdadmaeen.reservationsystem.model.dto.AvailableSlots;
import com.mehrdadmaeen.reservationsystem.model.dto.ReservationRequest;
import com.mehrdadmaeen.reservationsystem.model.dto.ReservationResponse;
import com.mehrdadmaeen.reservationsystem.repositories.ReservationRepository;
import com.mehrdadmaeen.reservationsystem.repositories.TableRepository;
import com.mehrdadmaeen.reservationsystem.repositories.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public boolean  duplicateReservation(ReservationRequest reservationRequest){
        return reservationRepository.findByReservationDateAndTable_IdAndReservationTime_Id(reservationRequest.getReservationDate(),
                tableRepository.findByTableName(reservationRequest.getTableName(), TableEntity.class).get().getId(),
                timeSlotRepository.findByReservationTime(reservationRequest.getReservationTime(),TimeSlotEntity.class).get().getId(),
                ReservationEntity.class).isPresent();

    }
    @Transactional(isolation= Isolation.SERIALIZABLE)
    public ResponseEntity<ReservationResponse> createUpdateReservation(ReservationRequest reservationRequest, ReservationEntity reservationEntity){
        if (duplicateReservation(reservationRequest))
            return new ResponseEntity(new ReservationResponse(0L,"UNAVAILABLE"), HttpStatus.NOT_ACCEPTABLE);

        reservationEntity.setName(reservationRequest.getName());
        reservationEntity.setReservationDate(reservationRequest.getReservationDate());
        reservationEntity.setContact(reservationRequest.getContact());
        reservationEntity.setTable(tableRepository.findByTableName(reservationRequest.getTableName(),TableEntity.class).get());
        reservationEntity.setReservationTime(timeSlotRepository.findByReservationTime(reservationRequest.getReservationTime(), TimeSlotEntity.class).get());
        reservationRepository.saveAndFlush(reservationEntity);
        return new ResponseEntity(new ReservationResponse(reservationEntity.getId(),"BOOKED"),HttpStatus.CREATED);

    }
    public List<AvailableSlots> retrieveAvailableSlots(LocalDate date){

        List<ReservationEntity> reserved;
        Long tableCount=tableRepository.count();
        Long slotCount=timeSlotRepository.count();
        List<AvailableSlots> available=new ArrayList<>();
        boolean match;
        reserved= reservationRepository.findAllByReservationDate(date,ReservationEntity.class);
        for (int i=1; i<=tableCount; i++){

            for(int j=1;j<=slotCount;j++) {

                match = false;

                for (ReservationEntity reservationEntity : reserved) {

                    if (reservationEntity.getTable().getTableName().equals(String.format("table%d", i)) &&
                            reservationEntity.getReservationTime().getTimeSlot() == j) {
                        match = true;
                    }
                    if (match) break;
                }

                if (!match) available.add(new AvailableSlots(String.format("table%d", i), date,
                        timeSlotRepository.findByTimeSlot(j, TimeSlotEntity.class).get().getReservationTime()));
            }
        }
        return available;
    }

}
