package com.hotel.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomRequest {
    private Long roomId;
    private String title;
    private int acreage;
    private String bedTypeName;
    private int guest;
    private int childrent;
    private String description;
    private boolean bookable;
    private String pageUrl;
    private Instant modifiedDate;
    private Set<FacilitiesResponse> facilities;
}
