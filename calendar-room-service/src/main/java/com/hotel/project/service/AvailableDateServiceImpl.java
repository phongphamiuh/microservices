package com.hotel.project.service;

import com.hotel.project.dto.AvailableDateResponse;
import com.hotel.project.dto.RoomCalendarAvailableResponse;
import com.hotel.project.mapper.AvailableDateMapper;
import com.hotel.project.mapper.RoomCalendarMapper;
import com.hotel.project.model.AvailableDate;
import com.hotel.project.model.RoomCalendar;
import com.hotel.project.repository.AvailableDateRepository;
import com.hotel.project.repository.RoomCalendarRepository;
import com.netflix.discovery.converters.Auto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AvailableDateServiceImpl implements AvailableDateService{
    private final AvailableDateRepository availableDateRepository;
    private final AvailableDateMapper availableDateMapper;

    @Override
    public void saveAvailableDate(LocalDate startDate, LocalDate endDate, Long hotelId) {
        for(LocalDate date=startDate;date.isBefore(endDate.plusDays(1));date=date.plusDays(1)){
            AvailableDate availableDate=new AvailableDate();
            if(availableDateRepository.findByDateAndHotelId(date,hotelId)==null){
                availableDate.setDate(date);
                availableDate.setDay(date.getDayOfMonth());
                availableDate.setMounth(date.getMonthValue());
                availableDate.setYear(date.getYear());
                availableDate.setDayOfWeek(date.getDayOfWeek().name());
                availableDate.setHotelId(hotelId);
            }
            availableDateRepository.save(availableDate);
        }
    }

    @Override
    public List<AvailableDateResponse> getAllAvailableDateByHotelId(Long hotelId,LocalDate start,LocalDate end) {

        List<AvailableDateResponse> result=new ArrayList<>();
        List<AvailableDate> availableDate=availableDateRepository.findAllByHotelIdAndBetweenStarAndEndDate(hotelId,start,end);
        for (AvailableDate date : availableDate) {
            result.add(availableDateMapper.mapToDto(date));
        }
        return result;
    }


}
