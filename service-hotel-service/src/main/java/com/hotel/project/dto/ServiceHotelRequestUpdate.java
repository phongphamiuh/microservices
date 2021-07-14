package com.hotel.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceHotelRequestUpdate {
    private Long serviceHotelId;
    private String name;
    private double price;
    private int tax;
    private String icon;
    private String taxName;
    private boolean priceAble;
    private boolean checkAble;
    private Long hotelId;
}
