package com.hotel.project.apis;

import com.hotel.project.domain.ServiceHotel;
import com.hotel.project.dto.*;
import com.hotel.project.service.ServiceHotelService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/apis/servicehotel")
public class ServiceHotelApis {
    private final ServiceHotelService serviceHotelService;

    @PostMapping
    public ResponseEntity<ServiceHotel> post(@RequestBody ServiceHotelRequest serviceHotelRequest){
        return ResponseEntity.ok().body(serviceHotelService.save(serviceHotelRequest));
    }

    @PostMapping("/saveAll/{hotelId}")
    public ResponseEntity<String> saveAll(@PathVariable("hotelId")Long hotelId){
        serviceHotelService.saveAll(hotelId);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/{serviceHotelId}")
    public ResponseEntity<ServiceHotelResponse> getServiceById(@PathVariable("serviceHotelId")Long serviceHotelId){
        return ResponseEntity.ok(serviceHotelService.getServiceHotelById(serviceHotelId));
    }

    @GetMapping("/alls/{hotelId}/{checkAble}")
    public ResponseEntity<List<ServiceHotelResponse>> getAlls(@PathVariable("hotelId")Long hotelId,
                                                              @PathVariable(value = "checkAble",required = false)boolean checkAble){
        return ResponseEntity.ok().body(serviceHotelService.findAllSeriveHotelByHotelIdAndCheckAlbe(hotelId,checkAble));
    }

    @GetMapping("/alls/{hotelId}")
    public ResponseEntity<List<ServiceHotelResponse>> getAllsByHotelId(@PathVariable("hotelId")Long hotelId
                                                                        ){
        return ResponseEntity.ok().body(serviceHotelService.findAllSeriveHotelByHotelId(hotelId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id")Long id,@RequestBody ServiceUpdateRequest serviceUpdateRequest){
        serviceHotelService.updateServiceHotel(id,serviceUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{hotelId}")
    public ResponseEntity<Void> updateCheckAle(@PathVariable("hotelId") Long hotelId,
                                                 @RequestBody UpdateServiceMapper updateServiceMapper){
        System.out.println(updateServiceMapper);
        serviceHotelService.updateCheckAbleByNameService(hotelId,updateServiceMapper.getUpdateServices());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        serviceHotelService.delete(id);
        return ResponseEntity.ok().body("Delete success");
    }
}
