package com.hotel.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Builder
public class HotelFacilitiesRequest {
    private String name;
    private String icon;

    public HotelFacilitiesRequest(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }
}
