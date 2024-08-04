package com.arianit.cityguidebe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDto extends BaseDto{
    private Long gastronomeId;
    private String reservationDate;
    private Integer numberOfPeople;
    private String specialRequests;
    private String phoneNumber;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String firstName;
    private String lastName;
    String nameOfGastronome;

    public ReservationDto(Long id, Long gastronomeId, String reservationDate,
                          Integer numberOfPeople, String specialRequests,
                          String phoneNumber, String status, LocalDateTime createdAt,
                          LocalDateTime updatedAt, String firstName, String lastName) {
        super(id);
        this.gastronomeId = gastronomeId;
        this.reservationDate = reservationDate;
        this.numberOfPeople = numberOfPeople;
        this.specialRequests = specialRequests;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public ReservationDto(Long id, Long gastronomeId, String reservationDate,
                          Integer numberOfPeople, String specialRequests,
                          String phoneNumber, String status, String nameOfGastronome) {
        super(id);
        this.gastronomeId = gastronomeId;
        this.reservationDate = reservationDate;
        this.numberOfPeople = numberOfPeople;
        this.specialRequests = specialRequests;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.nameOfGastronome = nameOfGastronome;
    }
}