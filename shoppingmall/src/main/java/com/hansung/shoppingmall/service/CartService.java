package com.hansung.shoppingmall.service;

import com.hansung.shoppingmall.dto.CartItemRequestDto;
import com.hansung.shoppingmall.dto.CartItemResponseDto;
import com.hansung.shoppingmall.entity.Cart;
import com.hansung.shoppingmall.entity.CartItem;
import com.hansung.shoppingmall.entity.Item;
import com.hansung.shoppingmall.entity.User;
import com.hansung.shoppingmall.repository.CartItemRepository;
import com.hansung.shoppingmall.repository.CartRepository;
import com.hansung.shoppingmall.repository.ItemRepository;
import com.hansung.shoppingmall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public void  addItem(CartItemRequestDto requestDto){
        Item item = getItemOrThrow(requestDto.getItemId());
        Cart cart = getCartOrThrow(requestDto.getUserId());

        CartItem cartItem = cartItemRepository.findByCart_IdAndItem_Id(cart.getId(), item.getId())
                .map(ci -> {
                    ci.setQuantity(ci.getQuantity() + requestDto.getQuantity());
                    return ci;
                })
                .orElseGet(() -> CartItem.builder()
                        .cart(cart)
                        .item(item)
                        .quantity(requestDto.getQuantity())
                        .build());

        cartItemRepository.save(cartItem);
    }

    @Transactional(readOnly = true)
    public List<CartItemResponseDto> getCartItems(Long userId){
        Cart cart = getCartOrThrow(userId);
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());

        return cartItems.stream()
                .map(CartItemResponseDto::new)
                .collect(Collectors.toList());
    }


    public void deleteCartItem(Long cartItemId){
        CartItem cartItem = getCartItemOrThrow(cartItemId);
        cartItemRepository.delete(cartItem);
    }

    public void deleteAllCartItems(Long userId){
        Cart cart = getCartOrThrow(userId);
        cartItemRepository.deleteAllByCart(cart);
    }

    public CartItemResponseDto updateQuantity(Long cartItemId, int newQuantity){
        CartItem cartItem = getCartItemOrThrow(cartItemId);
        cartItem.setQuantity(newQuantity);
        return new CartItemResponseDto(cartItem);
    }

    // private helper methods
    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다."));
    }

    private Item getItemOrThrow(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 상품을 찾을 수 없습니다."));
    }

    private Cart getCartOrThrow(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 장바구니를 찾을 수 없습니다."));
    }

    private CartItem getCartItemOrThrow(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 장바구니 상품을 찾을 수 없습니다."));
    }
}
