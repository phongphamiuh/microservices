package com.hotel.project.mapper;

import com.hotel.project.dto.BedTypeResponse;
import com.hotel.project.entity.BedType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class BedTypeMapper {

    public abstract BedTypeResponse mapToDto(BedType bedType);
}
