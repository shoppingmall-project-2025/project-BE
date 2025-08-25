package com.hansung.shoppingmall.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class PagingItemsResponseDto {
    private List<ItemResponseDto> items;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int size;
    private boolean isFirst;
    private boolean isLast;

    // Page<T>를 DTO로 변환하는 정적 팩토리 메서드
    public static PagingItemsResponseDto pageToDto(Page<ItemResponseDto> itemPage) {
        return PagingItemsResponseDto.builder()
                .items(itemPage.getContent())
                .currentPage(itemPage.getNumber())
                .totalPages(itemPage.getTotalPages())
                .totalElements(itemPage.getTotalElements())
                .size(itemPage.getSize())
                .isFirst(itemPage.isFirst())
                .isLast(itemPage.isLast())
                .build();
    }


}
