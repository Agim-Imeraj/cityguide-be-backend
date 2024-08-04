package com.arianit.cityguidebe.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GastronomeDto extends BaseDto {
    private Long cityId;
    private Long userId;
    private String nameOfGastronome;
    private String schedule;
    private Double longitude;
    private Double latitude;
    private String typeOfGastronome;
    private boolean sponsored;
    private List<String> attachments;
    private Integer rating;
    private String description;
    private Double price;
    private String phoneNumber;
    private String address;
    private String website;

    public GastronomeDto(Long cityId, Long userId){
        this.cityId = cityId;
        this.userId = userId;
    }
}
