package com.hotel.project.dto;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AvailableDateResponse{
    private LocalDate date;
    private int day;
    private int mounth;
    private int year;
    private String dayOfWeek;
    private Long hotelId;
    private List<RoomCalendarAvailableResponse> roomCalendars;
}
