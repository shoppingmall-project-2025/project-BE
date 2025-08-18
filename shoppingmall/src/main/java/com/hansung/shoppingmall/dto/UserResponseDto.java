package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.User;
import lombok.Getter;


@Getter
public class UserResponseDto {
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
    }

    private final Long id;

    private final String userName;
}
