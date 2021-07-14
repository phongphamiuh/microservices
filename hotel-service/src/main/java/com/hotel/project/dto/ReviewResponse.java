package com.hotel.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewResponse implements Serializable {
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
    private String reviewType;
}
