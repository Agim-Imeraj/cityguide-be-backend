package com.arianit.cityguidebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Reservation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "gastronome_id", insertable = false, updatable = false)
    @JsonBackReference
    private Gastronome gastronome;

    @Column(name = "gastronome_id")
    private Long gastronomeId;

    private String reservationDate;

    private Integer numberOfPeople;

    private String specialRequests;

    @Column(nullable = false)
    private String phoneNumber;

    private String status;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

}