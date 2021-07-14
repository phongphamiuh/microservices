package com.hotel.project.apis;

import com.hotel.project.dto.AvailableDateResponse;
import com.hotel.project.service.AvailableDateService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/availabledate")
public class AvailableDateController {
    private final AvailableDateService availableDateService;
    @PostMapping
    public ResponseEntity<String> post(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate")LocalDate startDate,
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate")LocalDate endDate,
                                       @RequestParam("hotelId")Long hotelId){
        availableDateService.saveAvailableDate(startDate,endDate,hotelId);
       return ResponseEntity.ok().body("Save success");
    }

    @GetMapping("/all")
    public List<AvailableDateResponse> getAll(@RequestParam("hotelId")Long hotelId,
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("start")LocalDate start,
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("end")LocalDate end){
        return availableDateService.getAllAvailableDateByHotelId(hotelId,start,end);
    }
}
