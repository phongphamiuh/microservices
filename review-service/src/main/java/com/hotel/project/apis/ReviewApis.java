package com.hotel.project.apis;
import com.hotel.project.domain.Review;
import com.hotel.project.dto.ReviewRequest;
import com.hotel.project.dto.ReviewResponse;
import com.hotel.project.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/apis/review")
@AllArgsConstructor
public class ReviewApis {
    private final ReviewService reviewService;
    @PostMapping
    public ResponseEntity<Review> post(@RequestBody ReviewRequest reviewRequest){
        return ResponseEntity.ok().body(reviewService.save(reviewRequest));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> get(@PathVariable("reviewId")Long reviewId){
        return ResponseEntity.ok().body(reviewService.findReviewByReviewId(reviewId));
    }

    @GetMapping("/alls/{hotelId}")
    public ResponseEntity<List<ReviewResponse>> getAll(@PathVariable("hotelId")Long hotelId){
        return ResponseEntity.ok().body(reviewService.findAllReviewByHotelId(hotelId));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> update(@PathVariable("reviewId")Long reviewId,@RequestBody ReviewRequest reviewRequest){
        reviewService.updateReview(reviewId,reviewRequest);
        return  ResponseEntity.ok().body("Update success");
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable("reviewId")Long reviewId){
        reviewService.deleteReview(reviewId);
        return  ResponseEntity.ok().body("Delete success");
    }

}
