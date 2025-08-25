package com.hansung.shoppingmall.controller;

import com.hansung.shoppingmall.dto.ItemRequestDto;
import com.hansung.shoppingmall.dto.ItemResponseDto;
import com.hansung.shoppingmall.dto.PagingItemsResponseDto;
import com.hansung.shoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ItemController {
    private final ItemService itemService;

    // 상품 추가
    @PostMapping("/api/item")
    public ResponseEntity<?> create(@RequestBody ItemRequestDto requestDto){
        try {
            ItemResponseDto itemResponseDto = itemService.save(requestDto);
            return ResponseEntity.ok().body(Map.of("success", "상품 추가 완료"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // 상품 조회 (페이징 적용)
    @GetMapping("/api/item")
    public ResponseEntity<?> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ItemResponseDto> allItemsPage = itemService.getAllItems(pageable);
            PagingItemsResponseDto responseDto = PagingItemsResponseDto.pageToDto(allItemsPage);
            return ResponseEntity.ok().body(responseDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }


    // 단일 상품 조회
    @GetMapping("/api/item/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable("itemId")Long id){
        try {
            ItemResponseDto itemById = itemService.getItemById(id);
            return ResponseEntity.ok().body(itemById);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // 상품 삭제
    @DeleteMapping("/api/item/{itemId}")
    public ResponseEntity<?> delete(@PathVariable("itemId")Long id){
        try {
            itemService.delete(id);
            return ResponseEntity.ok().body(Map.of("success", "상품이 삭제되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // 상품 정보 수정
    @PutMapping("/api/item/{itemId}")
    public ResponseEntity<?> update(@PathVariable("itemId")Long id, @RequestBody ItemRequestDto requestDto){
        try {
            itemService.update(id, requestDto);
            return ResponseEntity.ok().body(Map.of("success","상품 수정 성공"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }


}
