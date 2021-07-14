package com.hotel.project.client;

import com.hotel.project.dto.RoomRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class RoomClientImpl implements RoomClient{
    @Override
    public RoomRequest getRoomByRoomId(Long roomId) {
        HashMap<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("roomId",roomId);
        ResponseEntity<RoomRequest> roomRequest=new RestTemplate()
                .getForEntity("http://localhost:8083/api/room/{roomId}", RoomRequest.class,uriVariables);
        return roomRequest.getBody();
    }
}
