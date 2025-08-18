package com.hansung.shoppingmall.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewRequestDto {
    @NotNull(message = "평점은 필수")
    @Min(value = 1,message = "평점은 1 이상")
    @Max(value = 5,message = "평점은 5 이하")
    private Integer rating;

    @NotBlank(message = "제목은 필수")
    private String title;

    @NotNull(message = "내용은 필수")
    private String content;
}
