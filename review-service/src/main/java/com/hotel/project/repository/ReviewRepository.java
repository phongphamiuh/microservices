package com.hotel.project.repository;

import com.hotel.project.domain.Review;
import com.hotel.project.domain.ReviewType;
import com.hotel.project.dto.ReviewStatisResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long>{
    
    List<Review> findAllByHotelId(Long hotelId);
    List<Review> findAllByHotelIdAndReviewType(Long hotelId,ReviewType reviewType);
    @Query(nativeQuery = true,value="select avg(r.location) from Review r group by r.hotelId=:hotelId")
    float findAllByHotelIdSumLocaltion(@Param("hotelId") Long hotelId);
}
