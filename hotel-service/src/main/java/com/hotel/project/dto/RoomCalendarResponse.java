package com.hotel.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class RoomCalendarResponse implements Serializable {
    private int quantity;
    private double price;
    private Long roomId;
    private Long discountPrice;
    private int discountPercent;

    private LocalDate date;
    private Long hotelId;
    private String dayOfWeek;

    private String title;
    private int acreage;
    private String bedTypeName;
    private int guest;
    private int childrent;
    private String description;
    private boolean bookable;
    private String pageUrl;
    private Set<FacilitiesResponse> facilities;
}
