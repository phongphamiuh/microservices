package com.hotel.project.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface HotelOutputChanel {
    String HOTEL_DELETE_OUTPUT="hotelDeleteOutput";
    String HOTEL_UPDATE_OUTPUT="hotelUpdateOutput";

    @Output(HOTEL_DELETE_OUTPUT)
    MessageChannel hotelDeleteOutput();


    @Output(HOTEL_UPDATE_OUTPUT)
    MessageChannel hotelDUpdateOutput();
}
