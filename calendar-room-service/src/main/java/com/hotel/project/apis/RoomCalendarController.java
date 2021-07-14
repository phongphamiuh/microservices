package com.hotel.project.apis;
import com.hotel.project.dto.RoomCalendarAvailableResponse;
import com.hotel.project.dto.RoomCalendarRequest;
import com.hotel.project.dto.RoomCalendarResponse;

import com.hotel.project.model.RoomCalendar;
import com.hotel.project.model.RoomOption;
import com.hotel.project.service.RoomCalendarService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/apis/roomcalendar")
@AllArgsConstructor
public class RoomCalendarController {
    private final RoomCalendarService roomCalendarService;

    @PostMapping
    public ResponseEntity<RoomCalendar> post(@RequestBody RoomCalendarRequest roomCalendarRequest){
        return ResponseEntity.ok().body(roomCalendarService.saveRoomCalendar(roomCalendarRequest));
    }

    @PostMapping("/alls")
    public ResponseEntity<List<RoomCalendar>> postAlls(@RequestBody RoomCalendarRequest roomCalendarRequest,
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate")LocalDate startDate,
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate")LocalDate endDate){
        return ResponseEntity.ok().body(roomCalendarService.saveListRoomCalendar(roomCalendarRequest,startDate,endDate));
    }


    @GetMapping("/{roomCalendarId}")
    public ResponseEntity<RoomCalendarResponse> get(@PathVariable("roomCalendarId")Long roomCalendarId){
        return ResponseEntity.ok().body(roomCalendarService.get(roomCalendarId));
    }

    @GetMapping("/alls/{hotelId}")
    public ResponseEntity<List<RoomCalendarResponse>> getForHotelId1(@PathVariable("hotelId")Long hotelId){
        return ResponseEntity.ok().body(roomCalendarService.getAllsByHotelId(hotelId));
    }

    @GetMapping("/alls/hotelId/{hotelId}")
    public ResponseEntity<List<RoomCalendarAvailableResponse>> getForHotelId(@PathVariable("hotelId")Long hotelId){
        return ResponseEntity.ok().body(roomCalendarService.getAvailableAllsByHotelId(hotelId));
    }


    @GetMapping("/alls/date/{hotelId}")
    public ResponseEntity<List<RoomCalendarResponse>> getForHotelIdAndDate(@PathVariable("hotelId")Long hotelId){
        return ResponseEntity.ok().body(roomCalendarService.getAllsByDateNowAndHotelId(hotelId));
    }

    @GetMapping("/room")
    public ResponseEntity<RoomCalendarResponse> getRoomCalendarByNowAndHotelIdAndRoomId(@RequestParam("hotelId")Long hotelId,@RequestParam("roomId")Long roomId){
        return ResponseEntity.ok().body(roomCalendarService.getByDateNwAndHotelIdAndRoomId(hotelId,roomId));
    }

    @PutMapping(value="/{roomCalendarId}")
    public ResponseEntity<String> update(@PathVariable("roomCalendarId")Long roomCalendarId,
                                         @RequestBody RoomCalendarRequest roomCalendarRequest){
        roomCalendarService.updateRoomCalendar(roomCalendarId,roomCalendarRequest);
        return ResponseEntity.ok().body("Update success");
    }


    @PutMapping(value="/update/quantity/rooms")
    public ResponseEntity<Void> updateRooms(@RequestParam("value")String value,
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate")LocalDate startDate,
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate")LocalDate endDate,
                                                          @RequestParam("hotelId")Long hotelId,
                                                          @RequestParam("roomId")List<Long> rooms,
                                                          @RequestParam("roomOption") RoomOption roomOption
                                                          ){
        roomCalendarService.updateRooms(startDate,endDate,rooms,hotelId,value,roomOption);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/update/quantity/room")
    public ResponseEntity<Void> updateQuantityWithRoom(@RequestParam("quantity")int quantity,
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate")LocalDate startDate,
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate")LocalDate endDate,
                                                               @RequestParam("hotelId")Long hotelId,
                                                               @RequestParam("roomId")Long room){
        roomCalendarService.updateQuantityWithRoom(startDate,endDate,room,hotelId,quantity);
        return ResponseEntity.noContent().build();

    }

    @PutMapping(value="/update/price/room")
    public ResponseEntity<Void> updatePriceWithRoom(@RequestParam("price")Double price,
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate")LocalDate startDate,
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate")LocalDate endDate,
                                                       @RequestParam("hotelId")Long hotelId,
                                                       @RequestParam("roomId")Long room){
        roomCalendarService.updatePriceWithRoom(startDate,endDate,room,hotelId,price);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{roomCalendarId}")
    public ResponseEntity<String> delete(@PathVariable("roomCalendarId")Long roomCalendarId){
        roomCalendarService.deleteRoomCalendar(roomCalendarId);
        return ResponseEntity.ok().body("Delete success");
    }

    @GetMapping("/alls/calendar/{hotelId}")
    public ResponseEntity<List<RoomCalendarResponse>> getAlsForHotelIdAndBeweenDates(@PathVariable("hotelId")Long hotelId,
                                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate")LocalDate startDate,
                                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate")LocalDate endDate,
                                                                                     @RequestParam("orderQuantity")int orderQuantity){
        return ResponseEntity.ok().body(roomCalendarService.getAllsByHotelIdAndStartDateAndEndDate(hotelId,startDate,endDate,orderQuantity));
    }

}
