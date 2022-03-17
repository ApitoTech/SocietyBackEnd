package com.societybank.society.repository;

import com.societybank.society.entity.WeeklyDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyDataRepository extends JpaRepository<WeeklyDataEntity, Integer> {
}