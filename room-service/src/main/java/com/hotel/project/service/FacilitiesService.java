package com.hotel.project.service;

import com.hotel.project.dto.FacilitiesResponse;
import com.hotel.project.entity.Facilities;

import java.util.List;

public interface FacilitiesService {
    List<FacilitiesResponse> getAlls();
}
