package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ReservationRequest(
        Long gastronomeId,
        String reservationDate,
        Integer numberOfPeople,
        String specialRequests,
        String phoneNumber,
        String status,
        String fullName
) {
}
