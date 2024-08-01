package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.ReservationDto;
import com.arianit.cityguidebe.dto.request.PageRequest;
import com.arianit.cityguidebe.dto.request.ReservationRequest;
import com.arianit.cityguidebe.dto.request.UpdateReservationRequest;
import com.arianit.cityguidebe.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation( @RequestBody @Valid ReservationRequest reservationRequest) {
        ReservationDto reservationDto = reservationService.create(reservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        ReservationDto reservationDto = reservationService.getById(id);
        return ResponseEntity.ok(reservationDto);
    }
    @GetMapping("/pagable")
    public Page<ReservationDto> getAllPagable(@Valid PageRequest pageRequest){
        return reservationService.getAllPagable(pageRequest);
    }
    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservationDtoList = reservationService.getAll();
        return ResponseEntity.ok(reservationDtoList);
    }

    @GetMapping("/userId")
    public ResponseEntity<List<ReservationDto>> getReservationsByUserId() {
        List<ReservationDto> reservationDtoList = reservationService.getByUserId();
        return ResponseEntity.ok(reservationDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> update(
            @PathVariable Long id,
            @RequestBody UpdateReservationRequest request
            ){
        ReservationDto reservationDto = reservationService.update(id,request);
        return new  ResponseEntity<>(reservationDto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
