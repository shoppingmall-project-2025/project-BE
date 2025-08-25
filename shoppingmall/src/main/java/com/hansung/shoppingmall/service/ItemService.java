package com.hansung.shoppingmall.service;

import com.hansung.shoppingmall.dto.ItemRequestDto;
import com.hansung.shoppingmall.dto.ItemResponseDto;
import com.hansung.shoppingmall.dto.UserRequestDto;
import com.hansung.shoppingmall.entity.Item;
import com.hansung.shoppingmall.repository.ItemRepository;
import com.hansung.shoppingmall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemResponseDto save(ItemRequestDto dto){
        Item item = Item.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .thumbnailImageUrl(dto.getThumbnailImage())
                .detailImageUrl(dto.getDetailImage())
                .description(dto.getDescription())
                .build();

        Item savedItem = itemRepository.save(item);
        return new ItemResponseDto(savedItem);
    }

    @Transactional(readOnly = true)
    public Page<ItemResponseDto> getAllItems(Pageable pageable){
        Page<Item> itemPage = itemRepository.findAll(pageable);
        return itemPage.map(ItemResponseDto::new);
    }

    @Transactional(readOnly = true)
    public ItemResponseDto getItemById(Long id){
        Item item = getItemOrThrow(id);
        return new ItemResponseDto(item);
    }

    public void delete(Long id){
        Item item = getItemOrThrow(id);
        itemRepository.delete(item);
    }

    public ItemResponseDto update(Long id, ItemRequestDto dto){
        Item item = getItemOrThrow(id);
        item.update(dto);
        return new ItemResponseDto(item);
    }

    // private helper method
    private Item getItemOrThrow(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 상품을 찾을 수 없습니다."));
    }
}
