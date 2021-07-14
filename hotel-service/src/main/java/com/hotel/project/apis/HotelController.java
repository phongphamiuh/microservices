package com.hotel.project.apis;

import com.hotel.project.dto.HotelOrderResponse;
import com.hotel.project.dto.HotelRequest;
import com.hotel.project.dto.HotelResponse;
import com.hotel.project.exception.InvalidArgumentException;
import com.hotel.project.model.entity.Hotel;
import com.hotel.project.model.entity.StarType;
import com.hotel.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels/")
public class HotelController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 10;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    private HotelService hotelService;

    @PostMapping()
    public ResponseEntity<Hotel> post(@RequestBody HotelRequest hotelRequest){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.save(hotelRequest));
    }

    @GetMapping(value = "/detail/{hotelId}")
    public ResponseEntity<HotelResponse> get(@PathVariable("hotelId")Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelByHotelId(hotelId));
    }

    @GetMapping(value = "/detail/name/{name}")
    public ResponseEntity<HotelResponse> getByName(@PathVariable("name")String name){
        return ResponseEntity.ok(hotelService.getHotelByHotelName(name));
    }

    @GetMapping(value = "/order/name/{name}/{roomId}")
    public ResponseEntity<HotelOrderResponse> getByNameAndRoomId(@PathVariable("name")String name, @PathVariable("roomId")Long roomId){
        return ResponseEntity.ok(hotelService.getHotelByHotelNameAndRoomId(name,roomId));
    }

    @GetMapping(value="/search")
    public ResponseEntity<List<HotelResponse>> searchHotel(@RequestParam(name="page",defaultValue = "0")Optional<Integer> page,
                                                            @RequestParam(name="size") Optional<Integer> pageSize,
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("checkin") LocalDate checkin,
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("checkout")LocalDate checkout,
                                                            @RequestParam(name="orderQuantity")int orderQuantity,
                                                            @RequestParam(name="city")String city,
                                                            @RequestParam(name="sort",required = false) String sort,
                                                            @RequestParam(name="category",required = false) String category,
                                                            @RequestParam(name="star",required = false) List<StarType> star,
                                                            @RequestParam(name="minPrice",required = false) Double minPrice,
                                                            @RequestParam(name="maxPrice",required = false) Double maxPrice){
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = page.filter(p -> p >= 1)
                .map(p -> p - 1)
                .orElse(INITIAL_PAGE);

        if (Objects.isNull(page) || page.get()< 0) {
            throw new InvalidArgumentException("Invalid page");
        }

        if (Objects.isNull(pageSize) || pageSize.get() < 0) {
            throw new InvalidArgumentException("Invalid pageSize");
        }
        List<HotelResponse> hotelResponses=hotelService.searchHotel(checkin,checkout,orderQuantity,city,page.get(),evalPageSize,sort,category,star,minPrice,maxPrice);
        return ResponseEntity.ok().body(hotelResponses);
    }

    @GetMapping(value="/all/count")
    public ResponseEntity<Long> getAllsCount(@RequestParam(name="city")String city,
                                                            @RequestParam(name="category",required = false) String category,
                                                            @RequestParam(name="star",required = false) List<StarType> star,
                                                            @RequestParam(name="minPrice",required = false) Double minPrice,
                                                            @RequestParam(name="maxPrice",required = false) Double maxPrice){
        Long hotelCount=hotelService.getAllsCount(city,category,star,minPrice,maxPrice);
        return ResponseEntity.ok().body(hotelCount);
    }

    @DeleteMapping(value="{hotelId}")
    public ResponseEntity<?> delete(@PathVariable("hotelId")Long hotelId){
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="{hotelId}")
    public ResponseEntity<String> update(@PathVariable("hotelId")Long hotelId,@RequestBody HotelRequest hotelRequest){
        hotelService.updateHotel(hotelId,hotelRequest);
        return ResponseEntity.ok().body("Update success");
    }

}
