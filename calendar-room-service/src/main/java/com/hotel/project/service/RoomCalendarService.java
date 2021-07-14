package com.hotel.project.service;
import com.hotel.project.dto.RoomCalendarAvailableResponse;
import com.hotel.project.dto.RoomCalendarRequest;
import com.hotel.project.dto.RoomCalendarResponse;
import com.hotel.project.model.RoomCalendar;
import com.hotel.project.model.RoomOption;

import java.time.LocalDate;
import java.util.List;

public interface RoomCalendarService {
    RoomCalendar saveRoomCalendar(RoomCalendarRequest roomCalendarRequest);
    List<RoomCalendar> saveListRoomCalendar(RoomCalendarRequest roomCalendarRequest,LocalDate startDate,LocalDate endDate);
    RoomCalendarResponse get(Long roomCalendarId);
    List<RoomCalendarResponse> getAllsByDateNowAndHotelId(Long hotelId);
    RoomCalendarResponse getByDateNwAndHotelIdAndRoomId(Long hotelId,Long roomId);
    List<RoomCalendarResponse> getAllsByHotelId(Long hotelId);
    List<RoomCalendarAvailableResponse> getAvailableAllsByHotelId(Long hotelId);
    void updateRoomCalendar(Long id, RoomCalendarRequest roomCalendarRequest);
    void deleteRoomCalendar(Long id);
    List<RoomCalendarResponse> getAllsByHotelIdAndStartDateAndEndDate(Long hotelId, LocalDate startDate,
                                                                      LocalDate endDate,int orderQuantity);
    // update quantity and price with one room or many room
    void updateRooms(LocalDate startDate, LocalDate endDate, List<Long> rooms, Long hotelId, String value, RoomOption roomOption);
    void updateQuantityWithRoom(LocalDate startDate,LocalDate endDate,Long room,Long hotelId,int quantity);
    void updatePriceWithRoom(LocalDate startDate,LocalDate endDate,Long room,Long hotelId,Double price);
}
