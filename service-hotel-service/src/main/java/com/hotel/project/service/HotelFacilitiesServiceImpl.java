package com.hotel.project.service;

import com.hotel.project.domain.HotelFacilities;
import com.hotel.project.dto.HotelFacilitiesRequest;
import com.hotel.project.dto.HotelFacilitiesResponse;
import com.hotel.project.mapper.HotelFacilitiesMapper;
import com.hotel.project.repository.HotelFacilitiesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelFacilitiesServiceImpl implements  HotelFacilitiesService{

    private final HotelFacilitiesRepository hotelFacilitiesRepository;
    private final HotelFacilitiesMapper hotelFacilitiesMapper;

    @Override
    public HotelFacilities saveHotelFacilities(HotelFacilitiesRequest hotelFacilitiesRequest) {
        HotelFacilities hotelFacilities=hotelFacilitiesMapper.map(hotelFacilitiesRequest);
        hotelFacilitiesRepository.save(hotelFacilities);
        return hotelFacilities;
    }

    @Override
    public List<HotelFacilitiesResponse> getAll() {
        return hotelFacilitiesRepository.findAll()
                .stream()
                .map(hotelFacilitiesMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
