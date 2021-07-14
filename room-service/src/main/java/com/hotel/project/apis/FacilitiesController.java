package com.hotel.project.apis;

import com.hotel.project.dto.FacilitiesResponse;
import com.hotel.project.service.FacilitiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
@AllArgsConstructor
public class FacilitiesController {
    private final FacilitiesService facilitiesService;
    @RequestMapping
    public ResponseEntity<List<FacilitiesResponse>> getAll(){
        return ResponseEntity.ok(facilitiesService.getAlls());
    }
}
