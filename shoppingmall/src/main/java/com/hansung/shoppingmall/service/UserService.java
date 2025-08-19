package com.hansung.shoppingmall.service;

import com.hansung.shoppingmall.dto.UserRequestDto;
import com.hansung.shoppingmall.dto.UserResponseDto;
import com.hansung.shoppingmall.entity.Cart;
import com.hansung.shoppingmall.entity.User;
import com.hansung.shoppingmall.repository.CartRepository;
import com.hansung.shoppingmall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public UserResponseDto save(UserRequestDto dto){
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());

        User savedUser=userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        return new UserResponseDto(savedUser);
    }

    public List<?> getAllUsers(){
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto getUserById(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not find"));
        return new UserResponseDto(user);
    }

    public void delete(Long id){
        if(!userRepository.existsById(id)){
            throw new NoSuchElementException("User not found");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDto update(Long id, UserRequestDto requestDto){
        User user=userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("User not find"));
        user.setUserName(requestDto.getUserName());
        user.setPassword(requestDto.getPassword());

        User updatedUser = userRepository.save(user);
        return new UserResponseDto(updatedUser);
    }
}
