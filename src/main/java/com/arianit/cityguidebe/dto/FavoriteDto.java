package com.arianit.cityguidebe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FavoriteDto {
    private Long id;
    private String nameOfUser;
    private List<Long> gastronomeDtoList;
}
