package com.hotel.project.service;

import com.hotel.project.dto.HotelOrderResponse;
import com.hotel.project.dto.HotelRequest;
import com.hotel.project.dto.HotelResponse;
import com.hotel.project.model.entity.Hotel;
import com.hotel.project.model.entity.StarType;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

public interface HotelService {
    Hotel save(HotelRequest hotelRequest);

    HotelResponse getHotelByHotelId(Long hotelId);

    HotelResponse getHotelByHotelName(String name);

    HotelOrderResponse getHotelByHotelNameAndRoomId(String name,Long roomId);

    void updateHotel(Long id,HotelRequest hotelRequest);

    void UpdatePriceDisplay(Long id, double newPriceDisplay);

    List<HotelResponse> searchHotel(LocalDate checkin,LocalDate checkout,int orderQuantity,
                                    String city, Integer page, Integer size, String sort,
                                    String categoryName, List<StarType> starType, Double minPrice, Double maxPrice);

    void deleteHotel(Long hotelId);

    Long getAllsCount(String city,String categoryName,List<StarType> starType,Double minPrice,Double maxPrice);

}
