package com.hotel.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomRequest {
    private String title;
    private int acreage;
    private String bedTypeName;
    private int guest;
    private int childrent;
    private String description;
    private String safeBox;
    private boolean bookable;
    private String pageUrl;
    private String photo;
    private Long hotelId;
    private Set<String> name;
}
