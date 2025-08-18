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
@RequestMapping("/api/cartItem")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartItemResponseDto> addItem(@RequestBody CartItemRequestDto requestDto){
        CartItemResponseDto cartItemResponseDto = cartService.addItem(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<CartItemResponseDto>> getCartItem(@RequestBody UserIdDto dto){
        List<CartItemResponseDto> cartItems = cartService.getCartItems(dto.getUserId());
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable("cartItemId")Long cartItemId){
        cartService.deleteCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCartItem(@RequestBody UserIdDto dto){
        cartService.deleteAllCartItems(dto.getUserId());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cartItemId}")
    public ResponseEntity<CartItemResponseDto> updateQuantity(@PathVariable("cartItemId")Long cartItemId, @RequestBody Map<String,Integer> updates){
        int newQuantity=updates.get("quantity");
        CartItemResponseDto updated = cartService.updateQuantity(cartItemId, newQuantity);
        return ResponseEntity.ok(updated);
    }


}
