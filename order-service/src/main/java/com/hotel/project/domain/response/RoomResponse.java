package com.hotel.project.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RoomResponse {
    private String title;
    private int acreage;
    private String bedType;
    private int guest;
    private int childrent;
    private String description;
    private boolean bookable;
    private double price;
    private String pageUrl;
    private Instant modifiedDate;
}
