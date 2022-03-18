package com.societybank.society.repository;

import com.societybank.society.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    PaymentEntity findByRazorpayOrderId(String orderId);
}