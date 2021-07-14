package com.hotel.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {
    
    private Long hotelId;

    private String name;

    private String address;

    private String district;

    private String city;

    private Integer star;

    private String description;

    private String phone;

    private String email;

    private String logoUrl;

    private Instant checkin;

    private Instant checkout;

    private String categoryName;

    private List<ReviewResponse> reviews;

    private List<RoomCalendarResponse> roomCalendarResponses;

    private int reviewCount;

    private float totalHotel;

    private float totalLocaltion;

    private float totalConvention;

    private float totalCleanLiness;

    private float totalPrice;

    private HotelReviewType hotelReviewType;

    private Double displayPrice;
}
