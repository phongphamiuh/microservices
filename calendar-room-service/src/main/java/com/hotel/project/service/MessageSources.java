package com.hotel.project.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface MessageSources {
    String ROOM_GET_OUTPUT="roomGetOutput";

    @Output(ROOM_GET_OUTPUT)
    MessageChannel roomGetOutput();



}
