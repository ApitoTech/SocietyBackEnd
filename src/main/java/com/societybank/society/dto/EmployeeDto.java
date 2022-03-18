package com.societybank.society.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {
    private Long id;
    private String empName;
    private Long mobileNumber;
    private Boolean isAdmin;
    private String password;
    private Boolean viewAccess;
    private Boolean addAccess;
    private Boolean updateAccess;
    private Boolean superAdmin;


}
