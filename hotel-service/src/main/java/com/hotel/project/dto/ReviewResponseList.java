package com.hotel.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ReviewResponseList implements Serializable {
    private List<ReviewResponse> reviewResponseList;

    public ReviewResponseList() {
        reviewResponseList = new ArrayList<>();
    }
}
