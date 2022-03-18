package com.societybank.society.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SubscriberDto implements Serializable {
    private Long id;
    private Long mobileNumber;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String occupation;
    private String society;
    private String address;
    private Long activeDaysLeft;
    private Boolean selfSubscription;
    private Long referenceEmp;
    private Long filliedBy;


}
