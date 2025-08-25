package com.hansung.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewRequestDto {
    private Integer rating;
    private String title;
    private String content;
}
