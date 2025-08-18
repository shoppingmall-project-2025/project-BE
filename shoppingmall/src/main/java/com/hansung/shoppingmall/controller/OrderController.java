package com.hansung.shoppingmall.controller;

import com.hansung.shoppingmall.dto.OrderRequestDto;
import com.hansung.shoppingmall.dto.OrderResponseDto;
import com.hansung.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order/user/{userId}")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/item/{itemId}")
    public ResponseEntity<OrderResponseDto> orderProductDirectly(
            @PathVariable("userId") Long userId,
            @PathVariable("itemId") Long itemId,
            @RequestBody OrderRequestDto requestDto){
        OrderResponseDto orderDirectly = orderService.createOrderDirectly(userId, itemId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDirectly);

    }

    @PostMapping("/cart")
    public ResponseEntity<OrderResponseDto> orderFromCart(
            @PathVariable("userId") Long userId,
            @RequestBody List<Long> cartItemIds){
        OrderResponseDto orderResponseDto = orderService.orderFromCart(userId, cartItemIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }


    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getUserOrders(@PathVariable("userId")Long userId){
        List<OrderResponseDto> userOrders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(userOrders);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(
            @PathVariable("userId")Long userId,
            @PathVariable("orderId")Long orderId){
        OrderResponseDto orderById = orderService.getOrderById(userId, orderId);
        return ResponseEntity.ok(orderById);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Void> cancelOrder(
            @PathVariable("userId")Long userId,
            @PathVariable("orderId")Long orderId){
        orderService.cancelOrder(userId,orderId);
        return ResponseEntity.noContent().build();
    }

}
