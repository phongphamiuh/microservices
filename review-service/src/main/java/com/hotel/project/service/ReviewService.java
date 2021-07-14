package com.hotel.project.service;

import com.hotel.project.domain.Review;
import com.hotel.project.domain.ReviewType;
import com.hotel.project.dto.ReviewRequest;
import com.hotel.project.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    Review save(ReviewRequest reviewRequest);
    ReviewResponse findReviewByReviewId(Long reviewId);
    List<ReviewResponse> findAllReviewByHotelId(Long hotelId);
    List<ReviewResponse> findAllReviewByReviewType(Long hotelId,ReviewType reviewType);
    void updateReview(Long id, ReviewRequest reviewRequest);
    void deleteReview(Long id);
}
