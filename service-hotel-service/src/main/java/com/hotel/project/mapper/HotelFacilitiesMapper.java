package com.hotel.project.mapper;

import com.hotel.project.domain.HotelFacilities;
import com.hotel.project.dto.HotelFacilitiesRequest;
import com.hotel.project.dto.HotelFacilitiesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class HotelFacilitiesMapper {
    public abstract HotelFacilities map(HotelFacilitiesRequest hotelFacilitiesRequest);
    public abstract HotelFacilitiesResponse mapToDto(HotelFacilities hotelFacilities);
}
