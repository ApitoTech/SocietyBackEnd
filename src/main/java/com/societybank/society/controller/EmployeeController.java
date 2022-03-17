package com.societybank.society.controller;

import com.societybank.society.dto.EmployeeDto;
import com.societybank.society.dto.EmployeeResponseModel;
import com.societybank.society.entity.EmployeeEntity;
import com.societybank.society.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<EmployeeResponseModel> getEmployees(){
        EmployeeResponseModel employeeResponseModel= new EmployeeResponseModel();
        employeeResponseModel.setEmployees(employeeService.findAll());
        employeeResponseModel.setDataCount(employeeResponseModel.getEmployees().size());
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseModel);
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDto> createEmployees(@RequestBody EmployeeDto employeeDto){
        employeeDto = employeeService.save(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable Long id){
        EmployeeEntity employeeEntity = employeeService.findById(id).get();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeDto> updateEmployees(@RequestBody EmployeeDto employeeDto){
        employeeDto = employeeService.update(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

}
