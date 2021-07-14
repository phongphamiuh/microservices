package com.hotel.project.apis;

import com.hotel.project.domain.Reservation;
import com.hotel.project.domain.ReservationStatus;
import com.hotel.project.domain.request.ReservationRequest;
import com.hotel.project.domain.response.ReservationResponse;
import com.hotel.project.domain.response.ReservationResponseDetail;
import com.hotel.project.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationResponseDetail> post(@RequestBody ReservationRequest reservationRequest){
        LocalDate.now();
        return ResponseEntity.ok().body(reservationService.save(reservationRequest));
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResponseDetail> get(@PathVariable("reservationId")Long reservationId){
        return ResponseEntity.ok().body(reservationService.getReservationByReservationId(reservationId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationResponse>> getList(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("start")LocalDate start,
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("end")LocalDate end,
                                                             @RequestParam("hotelId")Long hotelId,
                                                             @RequestParam(value="reservationStatus",required = false)ReservationStatus reservationStatus,
                                                             @RequestParam(value="text",required = false)String text){
        return ResponseEntity.ok().body(reservationService.findAllReservationByToday(start,end,hotelId,reservationStatus,text));
    }

    @PutMapping("/update")
    public ResponseEntity<ReservationResponseDetail> updateReservation(@RequestBody ReservationRequest reservationRequest
    ){
        return ResponseEntity.ok().body(reservationService.updateReservation(reservationRequest));
    }

    @GetMapping("/status/all")
    public ResponseEntity<ReservationStatus[]> getListStatus(){
        return ResponseEntity.ok(ReservationStatus.values());
    }

}
