package com.hansung.shoppingmall.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {
    @NotBlank(message = "사용자 id는 필수입니다")
    private String userName;

    @NotBlank(message = "사용자 비밀번호는 필수입니다")
    private String password;
}
