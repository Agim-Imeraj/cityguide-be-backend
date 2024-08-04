package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateReservationRequest(
        @NotNull Long id,
        Long gastronomeId,
        String reservationDate,
        Integer numberOfPeople,
        String specialRequests,
        String phoneNumber,
        String status,
        String fullName
) {
}
