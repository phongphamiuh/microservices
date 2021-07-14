package com.hotel.project.mapper;

import com.hotel.project.dto.*;
import com.hotel.project.model.entity.Category;
import com.hotel.project.model.entity.Hotel;
import com.hotel.project.model.entity.StarType;
import com.hotel.project.repository.HotelRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class HotelMapper {
    @Autowired
    private HotelRepository hotelRepository;
    @Mapping(target = "category",source = "category")
    @Mapping(target = "name",source = "hotelRequest.name")
    @Mapping(target = "address",source = "hotelRequest.address")
    @Mapping(target="checkin",expression = "java(java.time.Instant.now())")
    @Mapping(target = "modifiedDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "checkout", expression = "java(java.time.Instant.now())")
    public abstract Hotel map(HotelRequest hotelRequest, Category category);

    @Mapping(target = "hotelId",source = "hotel.hotelId")
    @Mapping(target = "name",source = "hotel.name")
    @Mapping(target = "address",source = "hotel.address")
    @Mapping(target = "district",source = "hotel.district")
    @Mapping(target = "city",source = "hotel.city")
    @Mapping(target = "logoUrl",source = "logoUrl")
    @Mapping(target = "star",expression = "java(getStar(hotel))")
    @Mapping(target = "description",source = "description")
    @Mapping(target = "phone",source = "phone")
    @Mapping(target = "email",source = "email")
    @Mapping(target = "checkin",source = "checkin")
    @Mapping(target = "checkout",source = "checkout")
    @Mapping(target = "categoryName",source = "hotel.category.name")
    public abstract HotelResponse mapToHotelDto(Hotel hotel);

    @Mapping(target = "hotelId",source = "hotel.hotelId")
    @Mapping(target = "name",source = "hotel.name")
    @Mapping(target = "address",source = "hotel.address")
    @Mapping(target = "district",source = "hotel.district")
    @Mapping(target = "city",source = "hotel.city")
    @Mapping(target = "logoUrl",source = "hotel.logoUrl")
    @Mapping(target = "star",expression = "java(getStar(hotel))")
    @Mapping(target = "description",source = "hotel.description")
    @Mapping(target = "phone",source = "hotel.phone")
    @Mapping(target = "email",source = "hotel.email")
    @Mapping(target = "checkin",source = "hotel.checkin")
    @Mapping(target = "checkout",source = "hotel.checkout")
    @Mapping(target = "categoryName",source = "hotel.category.name")
    @Mapping(target="reviews",source="reviews")
    @Mapping(target="roomCalendarResponses",source="roomCalendarResponses")
    @Mapping(target="reviewCount",expression = "java(getCountReview(reviews))")
    @Mapping(target="totalHotel",expression = "java(getTotal(reviews))")
    @Mapping(target="totalLocaltion",expression = "java(getTotalLocaltion(reviews))")
    @Mapping(target="totalConvention",expression = "java(getTotalConvention(reviews))")
    @Mapping(target="totalCleanLiness",expression = "java(getTotalCleanLiness(reviews))")
    @Mapping(target="totalPrice",expression = "java(getPrice(reviews))")
    @Mapping(target = "hotelReviewType",expression = "java(getReviewType(reviews))")
    @Mapping(target = "displayPrice",expression = "java(getdisplayPrice(roomCalendarResponses,hotel))")
    public abstract HotelResponse mapToHotelDtoApis(Hotel hotel, List<ReviewResponse> reviews, List<RoomCalendarResponse> roomCalendarResponses);

    @Mapping(target = "hotelId",source = "hotel.hotelId")
    @Mapping(target = "name",source = "hotel.name")
    @Mapping(target = "address",source = "hotel.address")
    @Mapping(target = "district",source = "hotel.district")
    @Mapping(target = "city",source = "hotel.city")
    @Mapping(target = "star",source = "hotel.star")
    @Mapping(target = "logoUrl",source = "hotel.logoUrl")
    @Mapping(target = "quantity",source = "roomCalendarResponse.quantity")
    @Mapping(target = "price",source = "roomCalendarResponse.price")
    @Mapping(target = "discountPrice",source = "roomCalendarResponse.discountPrice")
    @Mapping(target = "discountPercent",source = "roomCalendarResponse.discountPercent")
    @Mapping(target = "title",source = "roomCalendarResponse.title")
    @Mapping(target = "roomId",source = "roomCalendarResponse.roomId")
    @Mapping(target = "guest",source = "roomCalendarResponse.guest")
    @Mapping(target = "childrent",source = "roomCalendarResponse.childrent")
    @Mapping(target = "acreage",source = "roomCalendarResponse.acreage")
    @Mapping(target = "bedTypeName",source = "roomCalendarResponse.bedTypeName")
    public abstract HotelOrderResponse mapToHotelDtoApiRoom(Hotel hotel, RoomCalendarResponse roomCalendarResponse);

    Integer getStar(Hotel hotel){
        return hotel.getStar().getValue();
    }

    HotelReviewType getReviewType(List<ReviewResponse> list){
        float total=(float)list.stream().mapToDouble((r) -> r.getTotal()).average().orElse(0);
        if(total>8)
            return HotelReviewType.XUATSAC;
        else if(total>7)
            return HotelReviewType.TOT;
        else if(total>6)
            return HotelReviewType.KHA;
        else
            return HotelReviewType.TRUNGBINH;

    }

    Double getdisplayPrice(List<RoomCalendarResponse> roomCalendarResponses,Hotel hotel){
        if(roomCalendarResponses.size()==0){
            return 0.0;
        }
        RoomCalendarResponse roomCalendarResponse=roomCalendarResponses.get(0);
        if(!hotel.getDisplayPrice().equals(roomCalendarResponse.getPrice())){
            hotelRepository.updateDisplayPrice(roomCalendarResponse.getHotelId(),roomCalendarResponse.getPrice());
        }
        return roomCalendarResponse.getPrice();
    }

    int getCountReview(List<ReviewResponse> list){
        return list.size();
    }

    float getTotal(List<ReviewResponse> list){
        if(list.size()==0)
            return 0;
        return (float)list.stream().mapToDouble((r) -> r.getTotal()).average().orElse(0);
    }

    float getTotalLocaltion(List<ReviewResponse> list){
        if(list.size()==0)
            return 0;
        return (float)list.stream().mapToDouble((r) -> r.getLocation()).average().orElse(0);
    }

    float getTotalCleanLiness(List<ReviewResponse> list){
        if(list.size()==0)
            return 0;
        return (float)list.stream().mapToDouble((r) -> r.getCleanliness()).average().orElse(0);
    }

    float getTotalConvention(List<ReviewResponse> list){
        if(list.size()==0)
            return 0;
        return (float)list.stream().mapToDouble((r) -> r.getConvention()).average().orElse(0);
    }

    float getPrice(List<ReviewResponse> list){
        if(list.size()==0)
            return 0;
        return (float)list.stream().mapToDouble((r) -> r.getPrice()).average().orElse(0);
    }

    public abstract void updateHotel(HotelRequest hotelRequest, @MappingTarget Hotel hotel);

}
