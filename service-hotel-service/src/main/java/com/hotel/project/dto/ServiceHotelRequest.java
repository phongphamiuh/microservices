package com.hotel.project.dto;

import com.hotel.project.domain.HotelFacilities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceHotelRequest {
    private String name;
    private double price;
    private int tax;
    private String taxName;
    private boolean priceAble;
    private boolean checkAble;
    private Long hotelId;
}
