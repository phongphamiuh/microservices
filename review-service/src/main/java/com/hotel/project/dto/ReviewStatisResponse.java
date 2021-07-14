package com.hotel.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewStatisResponse {
    private float tlocation;
    private float tconvention;
    private float tcleanliness;
    private float tprice;
    private float ttotal;
}
