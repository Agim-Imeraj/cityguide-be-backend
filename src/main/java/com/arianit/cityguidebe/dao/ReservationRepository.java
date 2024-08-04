package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.dto.ReservationDto;
import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("SELECT new com.arianit.cityguidebe.dto.ReservationDto(r.id, r.gastronomeId, r.reservationDate, " +
            "r.numberOfPeople, r.specialRequests, r.phoneNumber, r.status, g.nameOfGastronome) " +
            "FROM Reservation r " +
            "JOIN r.gastronome g ON r.gastronomeId = g.id " +
            "JOIN User u ON r.userId = u.id " +
            "WHERE u.id = :userId AND r.status <> 'Cancelled'")
    List<ReservationDto> findByUserId(@Param("userId") Long userId);


    @Query("SELECT new com.arianit.cityguidebe.dto.ReservationDto(r.id, r.gastronomeId, r.reservationDate, " +
            " r.specialRequests,r.numberOfPeople, r.phoneNumber, r.status, r.fullName" +
            ") " +
            "FROM Reservation r " +
            "JOIN r.gastronome g " +
            "JOIN User u ON r.userId = u.id " +
            "WHERE g.id = :gastronomeId AND r.status <> 'Cancelled'")
    List<ReservationDto> findReservationsByGastronomeId(@Param("gastronomeId") Long gastronomeId);

}
