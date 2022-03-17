package com.societybank.society.service;

import com.societybank.society.dto.SubscriberDto;
import com.societybank.society.dto.SubscriberLoginRequestDto;
import com.societybank.society.entity.SubscriberEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface SubscriberService {

    SubscriberDto save(SubscriberDto subscriberDto);

    List<SubscriberDto> findAll();

    Optional<SubscriberEntity> findById(Long id);

    SubscriberDto update(SubscriberDto subscribers);

    void deleteSubscriber(Long id);

    ResponseEntity<?> generateOtp(final Long mobileNumber) throws ExecutionException;

    ResponseEntity<?> validateOTPProceed(final SubscriberLoginRequestDto subscriberLoginRequestDto)
            throws ExecutionException;


}
