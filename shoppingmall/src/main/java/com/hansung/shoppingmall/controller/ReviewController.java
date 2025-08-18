package com.hansung.shoppingmall.controller;

import com.hansung.shoppingmall.dto.ReviewRequestDto;
import com.hansung.shoppingmall.dto.ReviewResponseDto;
import com.hansung.shoppingmall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/users/{userId}/items/{itemId}")
    public ResponseEntity<ReviewResponseDto> createReview(
            @PathVariable("userId") Long userId,
            @PathVariable("itemId") Long itemId,
            @RequestBody ReviewRequestDto request){

        ReviewResponseDto review = reviewService.createReview(userId, itemId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Page<ReviewResponseDto>> getUserReviews(
            @PathVariable("userId") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt")));
        Page<ReviewResponseDto> reviewsByUser = reviewService.getReviewsByUser(userId, pageable);
        return ResponseEntity.ok(reviewsByUser);
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<Page<ReviewResponseDto>> getItemReviews(
            @PathVariable("itemId") Long itemId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable=PageRequest.of(page,size,Sort.by(Sort.Order.desc("createdAt")));
        Page<ReviewResponseDto> reviewsByItem=reviewService.getReviewsByItem(itemId,pageable);
        return ResponseEntity.ok(reviewsByItem);
    }

    @PutMapping("/{reviewId/users/{userId}")
    public ResponseEntity<ReviewResponseDto> replaceReview(
            @PathVariable("userId") Long userId,
            @PathVariable("reviewId") Long reviewId,
            @RequestBody ReviewRequestDto request){
        ReviewResponseDto reviewResponse = reviewService.replaceReview(userId, reviewId, request);
        return ResponseEntity.ok(reviewResponse);
    }

    @DeleteMapping("/{reviewId}/users/{userId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable("userId") Long userId,
            @PathVariable("reviewId") Long reviewId){
        reviewService.deleteReview(userId,reviewId);
        return ResponseEntity.noContent().build();
    }
}
