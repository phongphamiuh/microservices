package com.hotel.project.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.project.dto.ReviewResponse;
import com.hotel.project.dto.ReviewResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ReviewClientImpl implements  ReviewClient{

    public List<ReviewResponse> getAllsByHotelId2(Long hotelId){
        HashMap<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("hotelId",hotelId);
        ReviewResponseList response=new RestTemplate()
                .getForObject("http://localhost:8086/apis/review/alls/{hotelId}", ReviewResponseList.class,uriVariables);
        List<ReviewResponse> list=response.getReviewResponseList();
        return list;
    }


    public List<ReviewResponse> getAllsByHotelId1(Long hotelId){
        HashMap<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("hotelId",hotelId);
        ResponseEntity<Object[]> responseEntity =
                new RestTemplate().getForEntity("http://localhost:8086/apis/review/alls/{hotelId}", Object[].class,uriVariables);
        Object[] objects = responseEntity.getBody();
        //jackson error
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, ReviewResponse.class))
                .collect(Collectors.toList());
    }

    public List<ReviewResponse> getAllsByHotelId(Long hotelId){
        HashMap<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("hotelId",hotelId);
        ResponseEntity<ReviewResponse[]> responseEntity =
                new RestTemplate().getForEntity("http://localhost:8086/apis/review/alls/{hotelId}", ReviewResponse[].class,uriVariables);
        ReviewResponse[] userArray = responseEntity.getBody();
        return Arrays.stream(userArray)
                .collect(Collectors.toList());
    }
}
