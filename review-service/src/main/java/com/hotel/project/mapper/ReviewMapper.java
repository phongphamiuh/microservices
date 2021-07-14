package com.hotel.project.mapper;

import com.hotel.project.domain.Review;
import com.hotel.project.domain.ReviewType;
import com.hotel.project.dto.ReviewRequest;
import com.hotel.project.dto.ReviewResponse;
import com.hotel.project.dto.ReviewStatisResponse;
import com.hotel.project.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ReviewMapper {
    @Autowired
    private ReviewRepository reviewRepository;

    @Mapping(target="total",expression = "java(getTotal(review))")
    public abstract Review map(ReviewRequest reviewRequest);

    @Mapping(target="reviewId",source = "reviewId")
    public abstract ReviewResponse mapToDto(Review review);

    public abstract void update(ReviewRequest reviewRequest,@MappingTarget Review review);



    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
            }
        }
        return null;
    }

    float getTotal(Review review){
        float total=(float)(review.getCleanliness()+review.getPrice()+review.getLocation()+review.getConvention())/4;
        return total;
    }

    float totalLocation(Review review){
        float totalLocation=reviewRepository.findAllByHotelIdSumLocaltion(review.getHotelId());
        return totalLocation;
    }
}
