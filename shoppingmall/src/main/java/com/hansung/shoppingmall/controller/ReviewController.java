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

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

//    // 리뷰 생성
//    @PostMapping("/users/{userId}/items/{itemId}")
//    public ResponseEntity<?> createReview(
//            @PathVariable("userId") Long userId,
//            @PathVariable("itemId") Long itemId,
//            @RequestBody ReviewRequestDto request){
//        try {
//            ReviewResponseDto review = reviewService.createReview(userId, itemId, request);
//            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("success", review));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }

//    // 사용자별 리뷰 목록 조회
//    @GetMapping("/users/{userId}")
//    public ResponseEntity<?> getUserReviews(
//            @PathVariable("userId") Long userId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size){
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt")));
//        try {
//            Page<ReviewResponseDto> reviewsByUser = reviewService.getReviewsByUser(userId, pageable);
//            return ResponseEntity.ok().body(Map.of("success", reviewsByUser));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }

//    // 상품별 리뷰 목록 조회
//    @GetMapping("/items/{itemId}")
//    public ResponseEntity<?> getItemReviews(
//            @PathVariable("itemId") Long itemId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size){
//        Pageable pageable=PageRequest.of(page,size,Sort.by(Sort.Order.desc("createdAt")));
//        try {
//            Page<ReviewResponseDto> reviewsByItem = reviewService.getReviewsByItem(itemId, pageable);
//            return ResponseEntity.ok().body(Map.of("success", reviewsByItem));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }
//
//    // 리뷰 수정
//    @PutMapping("/{reviewId}/users/{userId}")
//    public ResponseEntity<?> replaceReview(
//            @PathVariable("userId") Long userId,
//            @PathVariable("reviewId") Long reviewId,
//            @RequestBody ReviewRequestDto request){
//        try {
//            ReviewResponseDto reviewResponse = reviewService.replaceReview(userId, reviewId, request);
//            return ResponseEntity.ok().body(Map.of("success", reviewResponse));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }
//
//    // 리뷰 삭제
//    @DeleteMapping("/{reviewId}/users/{userId}")
//    public ResponseEntity<?> deleteReview(
//            @PathVariable("userId") Long userId,
//            @PathVariable("reviewId") Long reviewId){
//        try {
//            reviewService.deleteReview(userId, reviewId);
//            return ResponseEntity.ok().body(Map.of("success", "리뷰가 삭제되었습니다."));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }
}
