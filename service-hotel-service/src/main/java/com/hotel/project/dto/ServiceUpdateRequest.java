package com.hotel.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceUpdateRequest {
    private String name;
    private double price;
    private int tax;
    private String taxName;
    private boolean priceAble;
}
