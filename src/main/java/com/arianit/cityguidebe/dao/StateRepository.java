package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
