package com.hotel.project.mapper;

import com.hotel.project.dto.AvailableDateResponse;
import com.hotel.project.model.AvailableDate;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract  class AvailableDateMapper {

    public abstract AvailableDateResponse mapToDto(AvailableDate availableDate);
}
