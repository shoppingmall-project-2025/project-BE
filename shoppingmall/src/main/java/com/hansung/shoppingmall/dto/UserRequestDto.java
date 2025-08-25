package com.hansung.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {
    private String userId;
    private String password;
    private String username;
    private String email;
    private String phoneNumber;
}
