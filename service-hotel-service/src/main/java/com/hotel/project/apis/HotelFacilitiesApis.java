package com.hotel.project.apis;

import com.hotel.project.dto.HotelFacilitiesResponse;
import com.hotel.project.service.HotelFacilitiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/apis/hotel/facilities")
public class HotelFacilitiesApis {
    private final HotelFacilitiesService hotelFacilitiesService;
    @RequestMapping("/all")
    public ResponseEntity<List<HotelFacilitiesResponse>> getAll(){
        return ResponseEntity.ok(hotelFacilitiesService.getAll());
    }
}
