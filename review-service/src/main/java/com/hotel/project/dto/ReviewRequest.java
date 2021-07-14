package com.hotel.project.dto;

import com.hotel.project.domain.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewRequest {
    private String title;
    private String comment;
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
    private ReviewType reviewType;
}
