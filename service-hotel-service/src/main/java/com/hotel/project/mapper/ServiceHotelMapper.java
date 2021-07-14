package com.hotel.project.mapper;

import com.hotel.project.domain.HotelFacilities;
import com.hotel.project.domain.ServiceHotel;
import com.hotel.project.dto.ServiceHotelRequest;
import com.hotel.project.dto.ServiceHotelResponse;
import com.hotel.project.dto.ServiceUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ServiceHotelMapper {

    public abstract ServiceHotel map(ServiceHotelRequest serviceHotelRequest, HotelFacilities hotelFacilities);

    @Mapping(target="name",source = "hotelFacilities.name")
    @Mapping(target="icon",source = "hotelFacilities.icon")
    public abstract ServiceHotelResponse mapToDto(ServiceHotel serviceHotel);

    public abstract void update(ServiceUpdateRequest serviceUpdateRequest, @MappingTarget ServiceHotel serviceHotel);
}
