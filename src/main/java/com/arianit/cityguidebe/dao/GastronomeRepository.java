package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.Gastronome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GastronomeRepository extends JpaRepository<Gastronome, Long> {
    List<Gastronome> findByCityId(Long cityId);

    @Query("SELECT g FROM Gastronome g WHERE g.userId = :userId")
    List<Gastronome> findByUserId(Long userId);


}
