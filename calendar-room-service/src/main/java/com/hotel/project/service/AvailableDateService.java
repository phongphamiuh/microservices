package com.hotel.project.service;

import com.hotel.project.dto.AvailableDateResponse;

import java.time.LocalDate;
import java.util.List;

public interface AvailableDateService {
    void saveAvailableDate(LocalDate startDate,LocalDate endDate,Long hotelId);
    List<AvailableDateResponse> getAllAvailableDateByHotelId(Long hotelId,LocalDate start,LocalDate end);
}
