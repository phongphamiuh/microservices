package com.hotel.project.service;

import com.hotel.project.client.ReviewClient;
import com.hotel.project.client.RoomCalendarClient;
import com.hotel.project.dto.*;
import com.hotel.project.exception.BadRequestException;
import com.hotel.project.exception.InvalidArgumentException;
import com.hotel.project.mapper.HotelMapper;
import com.hotel.project.model.entity.Category;
import com.hotel.project.model.entity.Hotel;
import com.hotel.project.model.entity.StarType;
import com.hotel.project.model.specs.HotelSpecs;
import com.hotel.project.repository.CategoryRepository;
import com.hotel.project.repository.HotelRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private  CategoryRepository categoryRepository;
    @Autowired
    private  HotelRepository hotelRepository;
    @Autowired
    private  HotelMapper hotelMapper;
    @Autowired
    private  ReviewClient reviewClient;
    @Autowired
    private  RoomCalendarClient roomCalendarClient;
//    @Autowired
//    private RabbitTemplate rabbit;

    @Autowired
    @Qualifier(HotelOutputChanel.HOTEL_DELETE_OUTPUT)
    private  MessageChannel productDeleteChannel;

    public Hotel save(HotelRequest hotelRequest){
        Category category=categoryRepository.findByName(
                hotelRequest.getCategoryName()).orElseThrow(()->new BadRequestException(hotelRequest.getCategoryName()));
        Hotel hotel=hotelMapper.map(hotelRequest,category);
        hotelRepository.save(hotel);
        return hotel;
    }

    public HotelResponse getHotelByHotelId(Long hotelId){
        Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->
            new BadRequestException("Hotel is not found")
        );
        List<ReviewResponse> responses=reviewClient.getAllsByHotelId(hotel.getHotelId());
        List<RoomCalendarResponse> roomCalendarResponses=roomCalendarClient.getAllsRoomCalendarHotelByDateAndHotelId(hotel.getHotelId());
        HotelResponse hotelResponse=hotelMapper.mapToHotelDtoApis(hotel,responses,roomCalendarResponses);
        return hotelResponse;
    }

    @Override
    public HotelResponse getHotelByHotelName(String name) {
        Hotel hotel=hotelRepository.findByName(name).orElseThrow(()->
                new BadRequestException("Hotel is not found")
        );
        List<ReviewResponse> responses=reviewClient.getAllsByHotelId(hotel.getHotelId());
        List<RoomCalendarResponse> roomCalendarResponses=roomCalendarClient.getAllsRoomCalendarHotelByDateAndHotelId(hotel.getHotelId());
        HotelResponse hotelResponse=hotelMapper.mapToHotelDtoApis(hotel,responses,roomCalendarResponses);
        return hotelResponse;
    }

    @Override
    public HotelOrderResponse getHotelByHotelNameAndRoomId(String name,Long roomId) {
        Hotel hotel=hotelRepository.findByName(name).orElseThrow(()->
                new BadRequestException("Hotel is not found")
        );
        List<ReviewResponse> responses=reviewClient.getAllsByHotelId(hotel.getHotelId());
        RoomCalendarResponse roomCalendarResponses=roomCalendarClient.getRoomCalendarByNowAndHotelIdAndRoomId(hotel.getHotelId(),roomId);
        HotelOrderResponse hotelOrderResponse=hotelMapper.mapToHotelDtoApiRoom(hotel,roomCalendarResponses);
        return hotelOrderResponse;
    }

    public void updateHotel(Long id, HotelRequest hotelRequest){
         Hotel hotel=hotelRepository.findById(id).orElseThrow(()->new BadRequestException("Hotel is not found"));
         hotelMapper.updateHotel(hotelRequest,hotel);
         hotel=hotelRepository.save(hotel);
    }

    @Override
    public void UpdatePriceDisplay(Long id, double newPriceDisplay) {
        Hotel hotel=hotelRepository.findById(id).orElseThrow(()->new BadRequestException("Hotel is not found"));
    }

    @Override
    public List<HotelResponse> searchHotel(LocalDate checkin, LocalDate checkout, int orderQuantity, String city, Integer page, Integer size, String sort, String categoryName, List<StarType> starTypes, Double minPrice, Double maxPrice) {
        List<HotelResponse> list=new ArrayList<>();
        PageRequest pageRequest;
        if (Objects.nonNull(sort)) {
            Sort sortRequest = getSort(sort);
            if (Objects.isNull(sortRequest)) {
                throw new InvalidArgumentException("Invalid sort parameter");
            }
            pageRequest = PageRequest.of(page, size, sortRequest);
        } else {
            pageRequest = PageRequest.of(page, size);
        }

        Specification<Hotel> combinations=
                Objects.requireNonNull(Specification.where(HotelSpecs.withCategory(categoryName)))
                        .and(HotelSpecs.withCity(city))
                        .and(HotelSpecs.withStar(starTypes))
                        .and(HotelSpecs.minPrice(minPrice))
                        .and(HotelSpecs.maxPrice(maxPrice));

        hotelRepository.findAll(combinations, pageRequest).forEach((hotel)->{
            List<ReviewResponse> responses=reviewClient.getAllsByHotelId(hotel.getHotelId());
            List<RoomCalendarResponse> roomCalendarResponses=roomCalendarClient.getAlsForHotelIdAndBeweenDates(hotel.getHotelId(),checkin,checkout,orderQuantity);
            list.add(hotelMapper.mapToHotelDtoApis(hotel,responses,roomCalendarResponses));
        });
        return list;
    }

    public void deleteHotel(Long hotelId){
        Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->new BadRequestException("Hotel is not found"));
        HotelResponse hotelResponse=hotelMapper.mapToHotelDto(hotel);
        hotelRepository.delete(hotel);
        productDeleteChannel.send(MessageBuilder.withPayload(hotelResponse).build());
      //  rabbit.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY,hotelResponse);
    }


    @Override
    public Long getAllsCount(String city,String categoryName, List<StarType> starType, Double minPrice, Double maxPrice) {
        Specification<Hotel> combinations=
                Objects.requireNonNull(Specification.where(HotelSpecs.withCategory(categoryName)))
                        .and(HotelSpecs.withCity(city))
                        .and(HotelSpecs.withStar(starType))
                        .and(HotelSpecs.minPrice(minPrice))
                        .and(HotelSpecs.maxPrice(maxPrice));
        return hotelRepository.count(combinations);
    }

    private Sort getSort(String sort) {
        switch (sort) {
            case "lowest":
                return Sort.by(Sort.Direction.ASC, "displayPrice");
            case "highest":
                return Sort.by(Sort.Direction.DESC, "displayPrice");
            default:
                return null;
        }
    }
}
