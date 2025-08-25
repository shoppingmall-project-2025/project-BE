package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.User;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UserResponseDto {
    private String userId;
    private String userName;
    private String phoneNumber;
    private String email;
}
