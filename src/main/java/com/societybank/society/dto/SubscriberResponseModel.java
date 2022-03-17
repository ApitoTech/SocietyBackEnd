package com.societybank.society.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubscriberResponseModel {
    int dataCount = 0;
    List<SubscriberDto> subscribers= null;
    SubscriberDto subscriberDto = null;

}

