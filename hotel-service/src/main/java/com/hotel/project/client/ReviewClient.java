package com.hotel.project.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.project.dto.ReviewResponse;
import com.hotel.project.dto.ReviewResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public interface ReviewClient {
    public List<ReviewResponse> getAllsByHotelId2(Long hotelId);
    public List<ReviewResponse> getAllsByHotelId1(Long hotelId);
    public List<ReviewResponse> getAllsByHotelId(Long hotelId);
}
