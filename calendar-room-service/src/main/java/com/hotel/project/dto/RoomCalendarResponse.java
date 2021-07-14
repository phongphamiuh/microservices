package com.hotel.project.dto;

import com.hotel.project.model.AvailableDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCalendarResponse {
    private int quantity;
    private double price;
    private Long roomId;
    private Long discountPrice;
    private int discountPercent;

    private LocalDate date;
    private Long hotelId;
    private String dayOfWeek;
    // info of room
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
