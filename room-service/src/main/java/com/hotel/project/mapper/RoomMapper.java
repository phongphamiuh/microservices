package com.hotel.project.mapper;

import com.hotel.project.dto.RoomRequest;
import com.hotel.project.dto.RoomResponse;
import com.hotel.project.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class RoomMapper {
    @Mapping(target = "modifiedDate", expression = "java(java.time.Instant.now())")
    public abstract Room map(RoomRequest roomRequest);

    @Mapping(target = "bedTypeName",source = "room.bedType.name")
    public abstract RoomResponse mapToRoomDto(Room room);

    public abstract void updateRoom(RoomRequest roomRequest, @MappingTarget Room room);
}
