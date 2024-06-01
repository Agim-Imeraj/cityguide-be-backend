package com.arianit.cityguidebe.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "favorites")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favorite extends BaseEntity {

    @Column(name = "name_of_user")
    private String nameOfUser;

    @Column(name = "gastronome_id")
    private Long gastronomeId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "gastronome_id", insertable = false, updatable = false)
    private Gastronome gastronome;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    private User user;
}
