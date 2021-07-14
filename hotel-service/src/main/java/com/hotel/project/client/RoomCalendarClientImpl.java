package com.hotel.project.client;

import com.hotel.project.dto.ReviewResponse;
import com.hotel.project.dto.RoomCalendarResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomCalendarClientImpl implements RoomCalendarClient{

    @Override
    public List<RoomCalendarResponse> getAllsRoomCalendarHotelByDateAndHotelId(Long hotelId) {
        HashMap<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("hotelId",hotelId);
        ResponseEntity<RoomCalendarResponse[]> responseEntity =
                new RestTemplate().getForEntity("http://localhost:8085/apis/roomcalendar/alls/date/{hotelId}", RoomCalendarResponse[].class,uriVariables);
        RoomCalendarResponse[] userArray = responseEntity.getBody();
        return Arrays.stream(userArray)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomCalendarResponse> getAlsForHotelIdAndBeweenDates(Long hotelId, LocalDate checkin, LocalDate checkout, int orderQuantity) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("hotelId",String.valueOf(hotelId));
        uriVariables.put("checkin",String.valueOf(checkin));
        uriVariables.put("checkout",String.valueOf(checkout));
        uriVariables.put("orderQuantity",String.valueOf(orderQuantity));
        ResponseEntity<RoomCalendarResponse[]> responseEntity =
                new RestTemplate().getForEntity("http://localhost:8085/apis/roomcalendar/alls/calendar/{hotelId}?startDate={checkin}" +
                        "&endDate={checkout}&orderQuantity={orderQuantity}", RoomCalendarResponse[].class,uriVariables);
        RoomCalendarResponse[] userArray = responseEntity.getBody();
        return Arrays.stream(userArray)
                .collect(Collectors.toList());
    }

    @Override
    public RoomCalendarResponse getRoomCalendarByNowAndHotelIdAndRoomId(Long hotelId, Long roomId) {
        HashMap<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("hotelId",hotelId);
        uriVariables.put("roomId",roomId);
        ResponseEntity<RoomCalendarResponse> roomCalendarResponse=new RestTemplate().getForEntity(
                "http://localhost:8085/apis/roomcalendar/room?hotelId={hotelId}&roomId={roomId}",
                RoomCalendarResponse.class,uriVariables);
        return roomCalendarResponse.getBody();
    }
}
