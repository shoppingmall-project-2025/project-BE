package com.hansung.shoppingmall.controller;

import com.hansung.shoppingmall.dto.CartItemRequestDto;
import com.hansung.shoppingmall.dto.CartItemResponseDto;
import com.hansung.shoppingmall.dto.UserIdDto;
import com.hansung.shoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // 장바구니 추가
    @PostMapping("/api/cart")
    public ResponseEntity<?> addItem(@RequestBody CartItemRequestDto requestDto) {
        try {
            cartService.addItem(requestDto);
            return ResponseEntity.ok().body(Map.of("success", "상품이 장바구니에 담겼습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // 장바구니 조회
    @GetMapping("/api/cart/{userId}")
    public ResponseEntity<?> getCartItem(@PathVariable Long userId) {
        try {
            List<CartItemResponseDto> cartItems = cartService.getCartItems(userId);
            if(cartItems.isEmpty()){
                return ResponseEntity.ok().body(Map.of("success", "장바구니가 비워져있습니다."));
            }
            return ResponseEntity.ok().body(cartItems);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // 장바구니 상품 삭제
    @DeleteMapping("/api/cart/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("cartItemId") Long cartItemId) {
        try {
            cartService.deleteCartItem(cartItemId);
            return ResponseEntity.ok().body(Map.of("success", "장바구니 상품이 삭제되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // 장바구니 전체 비우기
    @DeleteMapping("/api/cart/{userId}/deleteAll")
    public ResponseEntity<?> deleteAllCartItem(@PathVariable Long userId) {
        try {
            cartService.deleteAllCartItems(userId);
            return ResponseEntity.ok().body(Map.of("success", "장바구니가 비워졌습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // 장바구니 상품 수량 수정
    @PatchMapping("/api/cart/{cartItemId}")
    public ResponseEntity<?> updateQuantity(@PathVariable("cartItemId") Long cartItemId, @RequestBody CartItemRequestDto requestDto) {
        try {
            cartService.updateQuantity(cartItemId, requestDto.getQuantity());
            return ResponseEntity.ok().body(Map.of("success", "장바구니 아이템 수량변경 성공"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }


}
