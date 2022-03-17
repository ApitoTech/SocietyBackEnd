package com.societybank.society.service.impl;



import com.google.common.cache.LoadingCache;
import com.societybank.society.constant.ApiConstants;
import com.societybank.society.dto.SubscriberDto;
import com.societybank.society.dto.SubscriberLoginRequestDto;
import com.societybank.society.entity.EmployeeEntity;
import com.societybank.society.entity.SubscriberEntity;
import com.societybank.society.exception.InvalidUserIdException;
import com.societybank.society.exception.OneTimePasswordValidationFailureException;
import com.societybank.society.repository.EmployeeRepository;
import com.societybank.society.repository.SubscriberRepository;
import com.societybank.society.service.SubscriberService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {

    private final LoadingCache<Long, Integer> oneTimePasswordCache;

    SubscriberRepository subscriberRepository;

    EmployeeRepository employeeRepository;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, EmployeeRepository employeeRepository, LoadingCache<Long, Integer> oneTimePasswordCache) {
        this.subscriberRepository = subscriberRepository;
        this.employeeRepository = employeeRepository;
        this.oneTimePasswordCache = oneTimePasswordCache;
    }

    @Override
    public SubscriberDto save(SubscriberDto subscriberDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SubscriberEntity subscriberEntity = modelMapper.map(subscriberDto, SubscriberEntity.class);
        EmployeeEntity employeeEntity = employeeRepository.findById(subscriberDto.getReferenceEmp()).get();
        subscriberEntity.setReferenceEmp(employeeEntity);
        EmployeeEntity referedEntity = employeeRepository.findById(subscriberDto.getReferenceEmp()).get();
        subscriberEntity.setFilliedBy(referedEntity);
        subscriberRepository.save(subscriberEntity);
        //SubscriberDto returnData = modelMapper.map(subscriberEntity, SubscriberDto.class);
        return subscriberDto;
    }

    @Override
    public List<SubscriberDto> findAll() {
        List<SubscriberEntity> subscribers = subscriberRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<SubscriberDto> subscriberList = Arrays.asList(modelMapper.map(subscribers, SubscriberDto[].class));
        return subscriberList;
    }

    @Override
    public Optional<SubscriberEntity> findById(Long id) {
        return subscriberRepository.findById(id);
    }

    @Override
    public SubscriberDto update(SubscriberDto subscribers) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SubscriberEntity subscriberEntity = modelMapper.map(subscribers, SubscriberEntity.class);
        subscriberRepository.save(subscriberEntity);
        SubscriberDto returnData = modelMapper.map(subscriberEntity, SubscriberDto.class);
        return returnData;
    }

    @Override
    public void deleteSubscriber(Long id) {
        subscriberRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> generateOtp(Long mobileNumber) throws ExecutionException {
        final var subscriberEntity = subscriberRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new InvalidUserIdException());
        final var response = new JSONObject();

        if (oneTimePasswordCache.get(subscriberEntity.getId()) != null)
            oneTimePasswordCache.invalidate(subscriberEntity.getId());

        final var otp = new Random().ints(1, 100000, 999999).sum();
        oneTimePasswordCache.put(subscriberEntity.getId(), otp);
        generateMessage(String.valueOf(mobileNumber), otp);
        response.put(ApiConstants.OTP, otp);
        response.put(ApiConstants.MESSAGE, ApiConstants.OTP_GENERATION_SUCCESS);
        response.put(ApiConstants.TIMESTAMP, LocalDateTime.now().toString());
        return ResponseEntity.ok(response.toString());
    }

    private boolean validateOtp(final SubscriberEntity subscriberEntity, final Integer otp) throws ExecutionException {
        return oneTimePasswordCache.get(subscriberEntity.getId()).equals(otp);
    }

    public ResponseEntity<?> validateOTPProceed(final SubscriberLoginRequestDto subscriberLoginRequestDto)
            throws ExecutionException {
        final var subscriberEntity = subscriberRepository.findByMobileNumber(subscriberLoginRequestDto.getMobile()).orElseThrow(() -> new InvalidUserIdException());
        final var response = new JSONObject();
        if (validateOtp(subscriberEntity, subscriberLoginRequestDto.getOtp())) {
            oneTimePasswordCache.invalidate(subscriberEntity.getId());
            response.put(ApiConstants.MESSAGE, ApiConstants.OTP_VALIDATE_SUCCESS);
            response.put(ApiConstants.TIMESTAMP, LocalDateTime.now().toString());
            return ResponseEntity.ok(response.toString());
        } else
            throw new OneTimePasswordValidationFailureException();
    }

    private String generateMessage(String mobile, int otp ) {
        String uri = ApiConstants.API_URI +
                "?apikey=" + ApiConstants.API_KEY +
                "&senderid=" + ApiConstants.SENDER_ID +
                "&number=" + mobile +
                "&message=" + "Dear User, Your OTP for login to " + otp +
                ". Valid for 5 minutes. Please do not \n" +
                "share this OTP. Regards, My Dreams Technology Team";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }
}

