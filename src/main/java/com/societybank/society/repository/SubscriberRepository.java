package com.societybank.society.repository;

import com.societybank.society.entity.SubscriberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long> {
    Optional<SubscriberEntity> findByMobileNumber(Long mobileNumber);
}