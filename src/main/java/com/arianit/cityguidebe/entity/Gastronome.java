package com.arianit.cityguidebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "gastronomies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Gastronome extends BaseEntity{

    @Column(nullable = false)
    private String nameOfGastronome;

    @Column(length = 255)
    private String schedule;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(length = 500)
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "day")
    private Integer day =1;

    @Column(name = "phone_number")
    private String phoneNumber;
    @Column()
    private boolean sponsored;

    @Enumerated(EnumType.STRING)
    private TypeOfGastronome typeOfGastronome;

    @ManyToOne
    @JoinColumn(name = "city_id" , insertable = false, updatable = false)
    @JsonBackReference
    private City city;

    @Column(name = "city_id")
    private Long cityId;

    @ManyToOne
    @JoinColumn(name = "user_id" , insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @Column(name = "user_id")
    private Long userId;


    @OneToMany(mappedBy = "gastronome", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reservation> reservations;

    @ElementCollection
    @CollectionTable(name = "gastronome_attachments", joinColumns = @JoinColumn(name = "gastronome_id"))
    @Column(name = "attachment")
    private List<String> attachments;

    @Column(name = "address")
    private String address;

    @Column(name = "website")
    private String website;


}
