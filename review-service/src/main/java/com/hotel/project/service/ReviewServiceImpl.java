package com.hotel.project.service;

import com.hotel.project.domain.Review;
import com.hotel.project.domain.ReviewType;
import com.hotel.project.dto.ReviewRequest;
import com.hotel.project.dto.ReviewResponse;
import com.hotel.project.exception.BadRequestException;
import com.hotel.project.mapper.ReviewMapper;
import com.hotel.project.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Review save(ReviewRequest reviewRequest) {
        Review review=reviewMapper.map(reviewRequest);
        reviewRepository.save(review);
        return review;
    }

    @Override
    public ReviewResponse findReviewByReviewId(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()->new BadRequestException("Review not found"));
        ReviewResponse reviewResponse=reviewMapper.mapToDto(review);
        return reviewResponse;
    }

    @Override
    public List<ReviewResponse> findAllReviewByHotelId(Long hotelId) {
        List<ReviewResponse> result=new ArrayList<>();
        List<Review> list=reviewRepository.findAllByHotelId(hotelId);
        list.forEach((review -> {
            ReviewResponse reviewResponse=reviewMapper.mapToDto(review);
            result.add(reviewResponse);
        }));
        return result;
    }

    double getTotal(List<Review> list){
        return list.stream().mapToDouble((r) -> r.getTotal()).average().getAsDouble();
    }

    double getTotalLocaltion(List<Review> list){
        return list.stream().mapToDouble((r) -> r.getLocation()).average().getAsDouble();
    }

    double getTotalCleanLiness(List<Review> list){
        return list.stream().mapToDouble((r) -> r.getCleanliness()).average().getAsDouble();
    }

    double getTotalConvention(List<Review> list){
        return list.stream().mapToDouble((r) -> r.getConvention()).average().getAsDouble();
    }

    double getPrice(List<Review> list){
        return list.stream().mapToDouble((r) -> r.getPrice()).average().getAsDouble();
    }

    @Override
    public List<ReviewResponse> findAllReviewByReviewType(Long hotelId,ReviewType reviewType) {
        List<ReviewResponse> result=new ArrayList<>();
        List<Review> list=reviewRepository.findAllByHotelIdAndReviewType(hotelId,reviewType);
//        double averageHotel = getTotal(list);
//        double averageLocation = getTotalLocaltion(list);
//        double averageConvention = getTotalCleanLiness(list);
//        double averageCleanLiness =getTotalConvention(list);
//        double averagePrice = getPrice(list);
        list.forEach((review -> {
            ReviewResponse reviewResponse=reviewMapper.mapToDto(review);
//            reviewResponse.setReviewCount(list.size());
//            reviewResponse.setTotalHotel(averageHotel);
//            reviewResponse.setTotalLocaltion(averageLocation);
//            reviewResponse.setTotalCleanLiness(averageCleanLiness);
//            reviewResponse.setTotalConvention(averageConvention);
//            reviewResponse.setTotalPrice(averagePrice);
            result.add(reviewResponse);
        }));
        return result;
    }

    @Override
    public void updateReview(Long id, ReviewRequest reviewRequest) {
        Review review = reviewRepository.findById(id).orElseThrow(()->new BadRequestException("Review not found"));
        reviewMapper.update(reviewRequest,review);
        review=reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(()->new BadRequestException("Review not found"));
        reviewRepository.delete(review);
    }
}

