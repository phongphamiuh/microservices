package com.hotel.project.client;

import com.hotel.project.dto.RoomCalendarResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

public interface RoomCalendarClient {
    public List<RoomCalendarResponse> getAllsRoomCalendarHotelByDateAndHotelId(Long hotelId);
    public List<RoomCalendarResponse> getAlsForHotelIdAndBeweenDates(Long hotelId, LocalDate checkin,LocalDate checkout,int orderQuantity);
    public RoomCalendarResponse getRoomCalendarByNowAndHotelIdAndRoomId(Long hotelId,Long roomId);
}
