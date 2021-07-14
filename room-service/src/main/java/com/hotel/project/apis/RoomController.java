package com.hotel.project.apis;

import com.hotel.project.dto.RoomRequest;
import com.hotel.project.dto.RoomResponse;
import com.hotel.project.entity.Room;
import com.hotel.project.service.RoomService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/room")
public class RoomController {

    private RoomService roomService;
    private static final Logger log= LoggerFactory.getLogger(RoomController.class);

    @PostMapping
    public ResponseEntity<RoomResponse> post(@RequestBody RoomRequest roomRequest){
        return ResponseEntity.ok(roomService.save(roomRequest));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponse> get(@PathVariable("roomId")Long roomId){
        RoomResponse roomResponse=roomService.getRoom(roomId);
        return ResponseEntity.ok(roomResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomResponse>> getAll(){
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomResponse>> getAllsByHotelId(@PathVariable("hotelId")Long hotelId){
        return ResponseEntity.ok(roomService.getAllsByHotelId(hotelId));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> delete(@PathVariable("roomId")Long roomId){
        roomService.deleteRoom(roomId);
        return ResponseEntity.ok().body("Delete success");
    }

    @PutMapping(value="/{roomId}")
    public ResponseEntity<Void> update(@PathVariable("roomId")Long roomId,@RequestBody RoomRequest hotelRequest){
        roomService.updateRoom(roomId,hotelRequest);
        return ResponseEntity.noContent().build();
    }
}
