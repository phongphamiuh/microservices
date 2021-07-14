package com.hotel.project.dto;

import com.hotel.project.domain.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewResponse {
    private Long reviewId;
    private String title;
    private String comment;
    private LocalDate createDate;
    private float location;
    private float convention;
    private float cleanliness;
    private float price;
    private float total;
    private Long userId;
    private Long hotelId;
    private Long reservationId;
    private LocalDate checkin;
    private String roomName;
    private String reviewType;
//    private int reviewCount;
//    private double totalHotel;
//    private double totalLocaltion;
//    private double totalConvention;
//    private double totalCleanLiness;
//    private double totalPrice;
//    private int collaborateCount;
}
