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
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public void createUser(UserRequestDto dto){
        //id 중복 검증
        if(userRepository.findByUserId(dto.getUserId()) != null){
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }
        //유저 생성
        User user = User.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id){
        User user = getUserOrThrow(id);
        return user.entityToDto(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public void update(Long id, UserRequestDto requestDto){
        User findUser = getUserOrThrow(id);
        findUser.update(requestDto);
    }
    private User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다."));
    }




}
