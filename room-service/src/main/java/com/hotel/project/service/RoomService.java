package com.hotel.project.service;

import com.hotel.project.dto.RoomRequest;
import com.hotel.project.dto.RoomResponse;
import com.hotel.project.entity.Room;
import java.util.List;

public interface RoomService {
    RoomResponse save(RoomRequest roomRequest);
    RoomResponse getRoom(Long roomId);
    List<RoomResponse> getAll();
    void deleteRoom(Long roomId);
    void updateRoom(Long id,RoomRequest roomRequest);
    List<RoomResponse> getAllsByHotelId(Long hotelId);
}
