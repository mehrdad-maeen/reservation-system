package com.mehrdadmaeen.reservationsystem;


import com.mehrdadmaeen.reservationsystem.model.ReservationEntity;
import com.mehrdadmaeen.reservationsystem.model.dto.AvailableSlots;
import com.mehrdadmaeen.reservationsystem.model.dto.ReservationRequest;
import com.mehrdadmaeen.reservationsystem.model.dto.ReservationResponse;


import com.mehrdadmaeen.reservationsystem.model.dto.RetrieveReservationsDto;
import com.mehrdadmaeen.reservationsystem.repositories.ReservationRepository;

import com.mehrdadmaeen.reservationsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/")
public class Controller {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;


    @GetMapping("availableSlots{date}")
    public List<AvailableSlots> RetrieveAvailableBasedDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return reservationService.retrieveAvailableSlots(date);
    }

    @GetMapping("reservations/{id}")
    public RetrieveReservationsDto RetrieveReservationBasedId(@PathVariable Long id) {
        return reservationRepository.findById(id, RetrieveReservationsDto.class).orElseThrow(()->new EntityNotFoundException(id.toString()));
    }

    @GetMapping("reservations{date}")
    public List<RetrieveReservationsDto> RetrieveReservationsBasedDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return reservationRepository.findAllByReservationDate(date,RetrieveReservationsDto.class);
    }

    @PostMapping("reservations")
    public ResponseEntity<ReservationResponse> createNewReservation(@RequestBody ReservationRequest reservationRequest) {
        ReservationEntity reservationEntity=new ReservationEntity();
        return reservationService.createUpdateReservation(reservationRequest,reservationEntity);
    }
    @PutMapping("reservations/{id}")
    public ResponseEntity<ReservationResponse> updateReservation(@PathVariable Long id,@RequestBody ReservationRequest reservationRequest){
        Optional<ReservationEntity> toUpdate = reservationRepository.findById(id, ReservationEntity.class);
        if(!toUpdate.isPresent())
            return new ResponseEntity(new ReservationResponse(0L,"UNAVAILABLE"),HttpStatus.NOT_ACCEPTABLE);

        return reservationService.createUpdateReservation(reservationRequest,toUpdate.get());
    }


    @DeleteMapping("reservations/{id}")
    public ResponseEntity<ReservationResponse> deleteReservation(@PathVariable Long id) {
        if(reservationRepository.findById(id,ReservationEntity.class).isPresent())
        reservationRepository.deleteById(id);
        return new ResponseEntity(new ReservationResponse(0L, "UNRESERVED"), HttpStatus.ACCEPTED);

    }
}
