package com.hansung.shoppingmall.service;

import com.hansung.shoppingmall.dto.ReviewRequestDto;
import com.hansung.shoppingmall.dto.ReviewResponseDto;
import com.hansung.shoppingmall.entity.Item;
import com.hansung.shoppingmall.entity.Review;
import com.hansung.shoppingmall.entity.User;
import com.hansung.shoppingmall.repository.ItemRepository;
import com.hansung.shoppingmall.repository.OrderItemRepository;
import com.hansung.shoppingmall.repository.ReviewRepository;
import com.hansung.shoppingmall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public ReviewResponseDto createReview(Long userId, Long itemId, ReviewRequestDto request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("no item"));

        if(!orderItemRepository.existsByOrder_User_IdAndItem_Id(userId,itemId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"상품을 구매한 회원만 작성 가능");
        }

        Review review = new Review();
        review.setItem(item);
        review.setUser(user);
        review.setRating(request.getRating());
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        Review saved = reviewRepository.save(review);

        return new ReviewResponseDto(saved);
    }

    @Transactional
    public Page<ReviewResponseDto> getReviewsByUser(Long userId, Pageable pageable){
        return reviewRepository.findByUser_Id(userId, pageable)
                .map(ReviewResponseDto::new);
    }

    @Transactional
    public Page<ReviewResponseDto> getReviewsByItem(Long itemId, Pageable pageable){
        return reviewRepository.findByItem_Id(itemId,pageable)
                .map(ReviewResponseDto::new);
    }

    @Transactional
    public ReviewResponseDto replaceReview(Long userId, Long reviewId, ReviewRequestDto request){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("no review"));

        if(!review.getUser().getId().equals(userId)){
            throw new IllegalStateException("본인만 수정 가능");
        }

        review.setRating(request.getRating());
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        return new ReviewResponseDto(review);
    }

    @Transactional
    public void deleteReview(Long userId,Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("no review"));

        if(!review.getUser().getId().equals(userId)){
            throw new IllegalStateException("본인만 삭제 가능");
        }
        reviewRepository.delete(review);
    }
}
