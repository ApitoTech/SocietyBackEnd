package com.societybank.society.controller;


import com.societybank.society.dto.SubscriberDto;
import com.societybank.society.dto.SubscriberResponseModel;
import com.societybank.society.entity.SubscriberEntity;
import com.societybank.society.service.EmployeeService;
import com.societybank.society.service.SubscriberService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriber")
public class SubscriberController {

    private SubscriberService subscriberService;
    private EmployeeService employeeService;

    @Autowired

    public SubscriberController(SubscriberService subscriberService, EmployeeService employeeService) {
        this.subscriberService = subscriberService;
        this.employeeService = employeeService;
    }


    @GetMapping
    public ResponseEntity<SubscriberResponseModel> getSubscribers(){
        SubscriberResponseModel subscriberResponseModel= new SubscriberResponseModel();
        subscriberResponseModel.setSubscribers(subscriberService.findAll());
        subscriberResponseModel.setDataCount(subscriberResponseModel.getSubscribers().size());
        return  ResponseEntity.status(HttpStatus.OK).body(subscriberResponseModel);
    }

    @PostMapping("/save")
    public ResponseEntity<SubscriberDto> createSubscribers(@RequestBody SubscriberDto subscriberDto){
        subscriberDto = subscriberService.save(subscriberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriberDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<SubscriberDto> findSubscriberById(@PathVariable Long id){
        SubscriberEntity subscriberEntity = subscriberService.findById(id).get();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SubscriberDto subscriberDto = modelMapper.map(subscriberEntity, SubscriberDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(subscriberDto);
    }

    @PutMapping("/update")
    public ResponseEntity<SubscriberDto> updateSubscribers(@RequestBody SubscriberDto subscriberDto){
        subscriberDto = subscriberService.update(subscriberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriberDto);
    }

    @DeleteMapping("{id}")
    public void deleteSubscriber(@PathVariable Long id){
        subscriberService.deleteSubscriber(id);
    }
}
