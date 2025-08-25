package com.hansung.shoppingmall.entity;

import com.hansung.shoppingmall.dto.UserRequestDto;
import com.hansung.shoppingmall.dto.UserResponseDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    public UserResponseDto entityToDto(User user){
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .userName(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }

    public void update(UserRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.username = requestDto.getUsername();
        this.password= requestDto.getPassword();
        this.email= requestDto.getEmail();
        this.phoneNumber= requestDto.getPhoneNumber();
    }
}
