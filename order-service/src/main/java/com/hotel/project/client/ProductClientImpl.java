package com.hotel.project.client;

import com.hotel.project.dto.RoomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class ProductClientImpl implements ProductClient{
    @Override
    public RoomResponse getRoomFromClient(Long roomId) {
        HashMap<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("roomId",roomId);
        ResponseEntity<RoomResponse> responseEntity = new RestTemplate().getForEntity
                ("http://localhost:8000/api/room/{roomId}",
                        RoomResponse.class, uriVariables);
        RoomResponse roomResponse = responseEntity.getBody();
        return roomResponse;
    }
}
