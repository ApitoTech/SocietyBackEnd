package com.societybank.society.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class EmployeeResponseModel {
    int dataCount = 0;

    List<EmployeeDto> employees = null;

    EmployeeDto employeeDto = null;


}
