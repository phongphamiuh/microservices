package com.hotel.project.client;

import com.hotel.project.dto.RoomResponse;

public interface ProductClient {
    public RoomResponse getRoomFromClient(Long roomId);
}
