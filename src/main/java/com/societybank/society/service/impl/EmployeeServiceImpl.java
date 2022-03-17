package com.societybank.society.service.impl;

import com.societybank.society.dto.EmployeeDto;
import com.societybank.society.entity.EmployeeEntity;
import com.societybank.society.repository.EmployeeRepository;
import com.societybank.society.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);
        EmployeeDto returnData = modelMapper.map(employeeEntity, EmployeeDto.class);
        return returnData;
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<EmployeeDto> employeesList = Arrays.asList(modelMapper.map(employees, EmployeeDto[].class));
        return employeesList;
    }

    @Override
    public Optional<EmployeeEntity> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public EmployeeDto update(EmployeeDto employees) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeEntity employeeEntity = modelMapper.map(employees, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);
        EmployeeDto returnData = modelMapper.map(employeeEntity, EmployeeDto.class);
        return returnData;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);

    }


}
