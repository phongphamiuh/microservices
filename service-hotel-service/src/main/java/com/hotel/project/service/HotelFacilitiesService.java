package com.hotel.project.service;

import com.hotel.project.domain.HotelFacilities;
import com.hotel.project.dto.HotelFacilitiesRequest;
import com.hotel.project.dto.HotelFacilitiesResponse;

import java.util.List;

public interface HotelFacilitiesService {
    HotelFacilities saveHotelFacilities(HotelFacilitiesRequest hotelFacilitiesRequest);
    List<HotelFacilitiesResponse> getAll();
}
