package com.hotel.project.dto;

import com.hotel.project.model.entity.StarType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelOrderResponse {

    private Long hotelId;
    private String name;
    private String address;
    private String district;
    private String city;
    private StarType star;
    private String logoUrl;

    private int quantity;
    private double price;
    private Long roomId;
    private Long discountPrice;
    private int discountPercent;

    private String title;
    private int guest;
    private int childrent;
    private int acreage;
    private String bedTypeName;
}
