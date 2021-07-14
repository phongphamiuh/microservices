package com.hotel.project.mapper;

import com.hotel.project.dto.RoomCalendarAvailableResponse;
import com.hotel.project.dto.RoomCalendarRequest;
import com.hotel.project.dto.RoomCalendarResponse;
import com.hotel.project.dto.RoomRequest;
import com.hotel.project.model.AvailableDate;
import com.hotel.project.model.RoomCalendar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class RoomCalendarMapper {
	
    @Mapping(target="quantity",source = "roomCalendarRequest.quantity")
    @Mapping(target="price",source = "roomCalendarRequest.price")
    @Mapping(target="roomId",source = "roomCalendarRequest.roomId")
    @Mapping(target="discountPrice",source = "roomCalendarRequest.discountPrice")
    @Mapping(target="discountPercent",source = "roomCalendarRequest.discountPercent")
    @Mapping(target="availableDate",source = "availableDate")
    @Mapping(target = "modifiedDate", expression = "java(java.time.Instant.now())")
    public abstract RoomCalendar map(RoomCalendarRequest roomCalendarRequest,AvailableDate availableDate);

    @Mapping(target="roomId",source = "roomCalendar.roomId")
    @Mapping(target="discountPrice",source = "roomCalendar.discountPrice")
    @Mapping(target="discountPercent",source = "roomCalendar.discountPercent")
    @Mapping(target="date",source = "roomCalendar.availableDate.date")
    @Mapping(target="hotelId",source = "roomCalendar.availableDate.hotelId")
    @Mapping(target="dayOfWeek",source = "roomCalendar.availableDate.dayOfWeek")
    @Mapping(target="title",source = "roomRequest.title")
    @Mapping(target="acreage",source = "roomRequest.acreage")
    @Mapping(target="bedTypeName",source = "roomRequest.bedTypeName")
    @Mapping(target="guest",source = "roomRequest.guest")
    @Mapping(target="childrent",source = "roomRequest.childrent")
    @Mapping(target="description",source = "roomRequest.description")
    @Mapping(target="bookable",source = "roomRequest.bookable")
    @Mapping(target="pageUrl",source = "roomRequest.pageUrl")
    @Mapping(target="facilities",source = "roomRequest.facilities")
    public abstract RoomCalendarResponse mapToRoomCalendarDto(RoomCalendar roomCalendar, RoomRequest roomRequest);

    public abstract RoomCalendarAvailableResponse mapToRoomCalendarAvailableDto(RoomCalendar roomCalendar);

    public abstract void updateRoomCalendar(RoomCalendarRequest roomCalendarRequest, @MappingTarget RoomCalendar roomCalendar);
}
