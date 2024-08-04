package com.arianit.cityguidebe.dto.request;

import com.arianit.cityguidebe.entity.TypeOfGastronome;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GastronomeRequest(
        @NotNull Long cityId,
        String nameOfGastronome,
        String schedule,
        @NotNull Double longitude,
        @NotNull Double latitude,
        boolean sponsored,
        TypeOfGastronome typeOfGastronome,
        List<String> attachments,
        Integer rating,
        String description,
        Double price,
        String phoneNumber,
        String website,
        String address
) {
}
