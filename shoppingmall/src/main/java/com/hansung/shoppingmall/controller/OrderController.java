package com.hansung.shoppingmall.controller;

import com.hansung.shoppingmall.dto.OrderRequestDto;
import com.hansung.shoppingmall.dto.OrderResponseDto;
import com.hansung.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order/user/{userId}")
public class OrderController {

    private final OrderService orderService;
//
//    // 상품 직접 주문
//    @PostMapping("/item/{itemId}")
//    public ResponseEntity<?> orderProductDirectly(
//            @PathVariable("userId") Long userId,
//            @PathVariable("itemId") Long itemId,
//            @RequestBody OrderRequestDto requestDto) {
//        try {
//            OrderResponseDto orderDirectly = orderService.createOrderDirectly(userId, itemId, requestDto);
//            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("success", orderDirectly));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }
//
//    // 장바구니에서 주문
//    @PostMapping("/cart")
//    public ResponseEntity<?> orderFromCart(
//            @PathVariable("userId") Long userId,
//            @RequestBody List<Long> cartItemIds) {
//        try {
//            OrderResponseDto orderResponseDto = orderService.orderFromCart(userId, cartItemIds);
//            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("success", orderResponseDto));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }
//
//    // 사용자 주문 목록 조회
//    @GetMapping
//    public ResponseEntity<?> getUserOrders(@PathVariable("userId") Long userId) {
//        try {
//            List<OrderResponseDto> userOrders = orderService.getUserOrders(userId);
//            return ResponseEntity.ok().body(Map.of("success", userOrders));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }
//
//    // 단일 주문 상세 조회
//    @GetMapping("/order/{orderId}")
//    public ResponseEntity<?> getOrderById(
//            @PathVariable("userId") Long userId,
//            @PathVariable("orderId") Long orderId) {
//        try {
//            OrderResponseDto orderById = orderService.getOrderById(userId, orderId);
//            return ResponseEntity.ok().body(Map.of("success", orderById));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }

    // 주문 취소
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<?> cancelOrder(
            @PathVariable("userId") Long userId,
            @PathVariable("orderId") Long orderId) {
        try {
            orderService.cancelOrder(userId, orderId);
            return ResponseEntity.ok().body(Map.of("success", "주문이 성공적으로 취소되었습니다."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
