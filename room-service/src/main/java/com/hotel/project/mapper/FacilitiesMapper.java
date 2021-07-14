package com.hotel.project.mapper;

import com.hotel.project.dto.FacilitiesResponse;
import com.hotel.project.entity.Facilities;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract  class FacilitiesMapper {
    public abstract FacilitiesResponse mapToDto(Facilities facilities);
}
