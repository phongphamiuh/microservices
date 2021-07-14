package com.hotel.project.service;

import com.hotel.project.client.RoomClient;
import com.hotel.project.dto.RoomCalendarAvailableResponse;
import com.hotel.project.dto.RoomCalendarRequest;
import com.hotel.project.dto.RoomCalendarResponse;
import com.hotel.project.dto.RoomRequest;
import com.hotel.project.exception.BadRequestException;
import com.hotel.project.mapper.RoomCalendarMapper;
import com.hotel.project.model.AvailableDate;
import com.hotel.project.model.RoomCalendar;
import com.hotel.project.model.RoomOption;
import com.hotel.project.repository.AvailableDateRepository;
import com.hotel.project.repository.RoomCalendarRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class RoomCalendarServiceImpl implements RoomCalendarService{
    @Autowired
    private RoomCalendarRepository roomCalendarRepository;
    @Autowired
    private RoomCalendarMapper roomCalendarMapper;
    @Autowired
    private AvailableDateRepository availableDateRepository;
    @Autowired
    @Qualifier(MessageSources.ROOM_GET_OUTPUT)
    private MessageChannel productDeleteChannel;
    @Autowired
    private RoomClient roomClient;

    @Override
    public RoomCalendar saveRoomCalendar(RoomCalendarRequest roomCalendarRequest) {
        RoomCalendar roomCalendar=null;
        AvailableDate availableDate=availableDateRepository.findByDateAndHotelId(roomCalendarRequest.getDate(),roomCalendarRequest.getHotelId());
        RoomCalendar roomCalendarCheck=roomCalendarRepository
                .findByDateAndRoomIdAndHotelId(roomCalendarRequest.getDate(),roomCalendarRequest.getRoomId(),roomCalendarRequest.getHotelId());

        if(roomCalendarCheck==null){
            roomCalendar=roomCalendarMapper.map(roomCalendarRequest,availableDate);
            roomCalendarRepository.save(roomCalendar);
        }
        return roomCalendar;
    }

    @Override
    public List<RoomCalendar> saveListRoomCalendar(RoomCalendarRequest roomCalendarRequest, LocalDate startDate, LocalDate endDate) {
        List<RoomCalendar> result=new ArrayList<>();
        for(LocalDate date=startDate;date.isBefore(endDate.plusDays(1));date=date.plusDays(1)) {
            AvailableDate availableDate = availableDateRepository.findByDateAndHotelId(date, roomCalendarRequest.getHotelId());
            result.add(roomCalendarMapper.map(roomCalendarRequest, availableDate));
        }
        roomCalendarRepository.saveAll(result);
        return result;
    }

    @Override
    public RoomCalendarResponse get(Long roomCalendarId) {
        RoomCalendar roomCalendar=roomCalendarRepository.findById(roomCalendarId).orElseThrow(()->new BadRequestException("Room Calendar not find"));
        RoomRequest roomRequest=roomClient.getRoomByRoomId(roomCalendar.getRoomId());
        RoomCalendarResponse roomCalendarResponse=roomCalendarMapper.mapToRoomCalendarDto(roomCalendar,roomRequest);
        return roomCalendarResponse;
    }

    @Override
    public List<RoomCalendarResponse> getAllsByHotelId(Long hotelId) {
        List<RoomCalendarResponse> result=new ArrayList<>();
        List<AvailableDate> availableDateList=availableDateRepository.findByHotelId(hotelId);
        availableDateList.forEach(availableDate -> {
            List<RoomCalendar> list=roomCalendarRepository.findAllByAvailableDate(availableDate);
            list.forEach((roomCalendar)->{
                RoomRequest roomRequest=roomClient.getRoomByRoomId(roomCalendar.getRoomId());
                result.add(roomCalendarMapper.mapToRoomCalendarDto(roomCalendar,roomRequest));
            });
        });
        return result;
    }


    @Override
    public List<RoomCalendarResponse> getAllsByDateNowAndHotelId(Long hotelId) {
        AvailableDate availableDate=availableDateRepository.findByDateAndHotelId(LocalDate.now(),hotelId);
        List<RoomCalendar> list=roomCalendarRepository.findAllByAvailableDate(availableDate);
        List<RoomCalendarResponse> result=new ArrayList<>();
        list.forEach((roomCalendar)->{
            RoomRequest roomRequest=roomClient.getRoomByRoomId(roomCalendar.getRoomId());
            result.add(roomCalendarMapper.mapToRoomCalendarDto(roomCalendar,roomRequest));
        });
        return result;
    }

    @Override
    public RoomCalendarResponse getByDateNwAndHotelIdAndRoomId(Long hotelId, Long roomId) {
        RoomCalendar roomCalendar=roomCalendarRepository.findByDateAndRoomIdAndHotelId(LocalDate.now(),roomId,hotelId);
        RoomRequest roomRequest=roomClient.getRoomByRoomId(roomCalendar.getRoomId());
        RoomCalendarResponse roomCalendarResponse=roomCalendarMapper.mapToRoomCalendarDto(roomCalendar,roomRequest);
        return roomCalendarResponse;
    }

    @Override
    public void updateRoomCalendar(Long id, RoomCalendarRequest roomCalendarRequest) {
        AvailableDate availableDate=availableDateRepository.findByDateAndHotelId(roomCalendarRequest.getDate(),roomCalendarRequest.getHotelId());
        RoomCalendar roomCalendar=roomCalendarRepository.findByAvailableDateAndRoomId(availableDate,roomCalendarRequest.getRoomId())
                .orElseThrow(()->new BadRequestException("Room Calendar not find"));
        roomCalendarMapper.updateRoomCalendar(roomCalendarRequest,roomCalendar);
        roomCalendar=roomCalendarRepository.save(roomCalendar);
    }

    @Override
    public void deleteRoomCalendar(Long id) {
        RoomCalendar roomCalendar=roomCalendarRepository
                .findById(id).orElseThrow(()->new BadRequestException("Room Calendar not find"));
        roomCalendarRepository.delete(roomCalendar);
    }

    @Override
    public List<RoomCalendarResponse> getAllsByHotelIdAndStartDateAndEndDate(Long hotelId, LocalDate startDate,
                                                                             LocalDate endDate,int orderQuantity) {
        List<RoomCalendarResponse> result=new ArrayList<>();
        List<RoomCalendar> list=roomCalendarRepository.findAllByHotelIdAndBeweentDates(hotelId,startDate,endDate);
        list.forEach((roomCalendar)->{
            if(roomCalendar.getQuantity()>=orderQuantity){
                RoomRequest roomRequest=roomClient.getRoomByRoomId(roomCalendar.getRoomId());
                result.add(roomCalendarMapper.mapToRoomCalendarDto(roomCalendar,roomRequest));
            }
            if(roomCalendar.getQuantity()==0){
                RoomRequest roomRequest=roomClient.getRoomByRoomId(roomCalendar.getRoomId());
                result.add(roomCalendarMapper.mapToRoomCalendarDto(roomCalendar,roomRequest));
            }
        });
        return result;
    }

    @Override
    public List<RoomCalendarAvailableResponse> getAvailableAllsByHotelId(Long hotelId) {
        List<RoomCalendarAvailableResponse> result=new ArrayList<>();
        List<AvailableDate> availableDateList=availableDateRepository.findByHotelId(hotelId);
        availableDateList.forEach(availableDate -> {
            List<RoomCalendar> list=roomCalendarRepository.findAllByAvailableDate(availableDate);
            list.forEach((roomCalendar)->{
                result.add(roomCalendarMapper.mapToRoomCalendarAvailableDto(roomCalendar));
            });
        });
        return result;
    }

    @Override
    public void updateRooms(LocalDate startDate, LocalDate endDate, List<Long> rooms, Long hotelId, String value, RoomOption roomOption) {
       if(roomOption.equals(RoomOption.QUANTITY)){
           rooms.forEach(roomId->{
               for(LocalDate date=startDate;date.isBefore(endDate.plusDays(1));date=date.plusDays(1)) {
                   RoomCalendar roomCaledar=roomCalendarRepository.findByDateAndRoomIdAndHotelId(date,roomId,hotelId);
                   if(Objects.isNull(roomCaledar)){
                       throw new BadRequestException("Room calendar not found");
                   }
                   roomCaledar.setQuantity( Integer.parseInt(value));

                   roomCaledar=roomCalendarRepository.save(roomCaledar);
               }
           });
       }
       if(roomOption.equals(RoomOption.RATE)){
           rooms.forEach(roomId->{
               for(LocalDate date=startDate;date.isBefore(endDate.plusDays(1));date=date.plusDays(1)) {
                   RoomCalendar roomCaledar=roomCalendarRepository.findByDateAndRoomIdAndHotelId(date,roomId,hotelId);
                   if(Objects.isNull(roomCaledar)){
                       throw new BadRequestException("Room calendar not found");
                   }
                   roomCaledar.setPrice( Double.parseDouble(value));
                   roomCaledar=roomCalendarRepository.save(roomCaledar);
               }
           });
       }
    }

    @Override
    public void updateQuantityWithRoom(LocalDate startDate, LocalDate endDate, Long room, Long hotelId, int quantity) {
        for(LocalDate date=startDate;date.isBefore(endDate.plusDays(1));date=date.plusDays(1)) {
            RoomCalendar roomCaledar=roomCalendarRepository.findByDateAndRoomIdAndHotelId(date,room,hotelId);
            if(Objects.isNull(roomCaledar)){
                throw new BadRequestException("Room calendar not found");
            }
            roomCaledar.setQuantity(quantity);
            roomCaledar=roomCalendarRepository.save(roomCaledar);
        }
    }

    @Override
    public void updatePriceWithRoom(LocalDate startDate, LocalDate endDate, Long room, Long hotelId, Double price) {
        for(LocalDate date=startDate;date.isBefore(endDate.plusDays(1));date=date.plusDays(1)) {
            RoomCalendar roomCaledar=roomCalendarRepository.findByDateAndRoomIdAndHotelId(date,room,hotelId);
            if(Objects.isNull(roomCaledar)){
                throw new BadRequestException("Room calendar not found");
            }
            roomCaledar.setPrice(price);
            roomCaledar=roomCalendarRepository.save(roomCaledar);
        }
    }
}
