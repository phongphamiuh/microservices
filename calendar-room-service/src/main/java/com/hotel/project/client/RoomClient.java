package com.hotel.project.client;

import com.hotel.project.dto.RoomRequest;

public interface RoomClient {
    RoomRequest getRoomByRoomId(Long hotelId);
}
