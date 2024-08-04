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
    private String nameOfGastronome;
    private String fullName;

    public ReservationDto(Long id, Long gastronomeId, String nameOfGastronome, String reservationDate,
                          Integer numberOfPeople, String specialRequests,
                          String phoneNumber, String status, String fullName) {
        super(id);
        this.gastronomeId = gastronomeId;
        this.reservationDate = reservationDate;
        this.numberOfPeople = numberOfPeople;
        this.specialRequests = specialRequests;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.fullName = fullName;
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