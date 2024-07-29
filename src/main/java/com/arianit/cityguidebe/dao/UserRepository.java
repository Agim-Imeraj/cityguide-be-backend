package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    //Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.deletedAt IS NULL")
    Optional<User> findById(Long id);

    boolean existsByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.deletedAt = :deletedAt WHERE u.id = :id")
    void markAsDeleted(@Param("id") Long id, @Param("deletedAt") LocalDateTime deletedAt);
}
