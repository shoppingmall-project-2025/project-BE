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

    public CartItemResponseDto addItem(CartItemRequestDto requestDto){
        int qty=requestDto.getQuantity();

        User user=userRepository.findById(requestDto.getUserId())
                .orElseThrow(()->new NoSuchElementException("no user"));

        Item item=itemRepository.findById(requestDto.getItemId())
                .orElseThrow(()->new NoSuchElementException("no item"));


        Cart cart=cartRepository.findByUser_Id(requestDto.getUserId())
                .orElseThrow(()->new NoSuchElementException("no cart"));


        CartItem cartItem=cartItemRepository.findByCart_IdAndItem_Id(cart.getId(),item.getId())
                .map(ci->{ci.setQuantity(ci.getQuantity()+qty); return ci;})
                .orElseGet(()->{
                    CartItem ci = new CartItem();
                    ci.setCart(cart);
                    ci.setItem(item);
                    ci.setQuantity(qty);
                    return ci;
                });

        CartItem save = cartItemRepository.save(cartItem);
        return new CartItemResponseDto(save);
    }

    public List<CartItemResponseDto> getCartItems(Long userId){
        Cart cart=cartRepository.findByUser_Id(userId)
                .orElseThrow(()->new NoSuchElementException("no user"));

        List<CartItem> cartItems = cartItemRepository.findByCart_Id(cart.getId());

        return cartItems.stream()
                .map(CartItemResponseDto::new)
                .collect(Collectors.toList());
    }

    public void deleteCartItem(Long cartItemId){
        if(!cartItemRepository.existsById(cartItemId)){
            throw new NoSuchElementException("no cartItem");
        }

        cartItemRepository.deleteById(cartItemId);
    }

    public void deleteAllCartItems(Long userId){
        Cart cart=cartRepository.findByUser_Id(userId)
                .orElseThrow(()->new NoSuchElementException("no cart"));

        cartItemRepository.deleteByCart_Id(cart.getId());
    }

    public CartItemResponseDto updateQuantity(Long cartItemId, int newQuantity){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new NoSuchElementException("no cartItem"));

        cartItem.setQuantity(newQuantity);
        CartItem save = cartItemRepository.save(cartItem);
        return new CartItemResponseDto(save);
    }
}
