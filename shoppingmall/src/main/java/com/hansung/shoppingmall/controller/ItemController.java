package com.hansung.shoppingmall.controller;

import com.hansung.shoppingmall.dto.ItemRequestDto;
import com.hansung.shoppingmall.dto.ItemResponseDto;
import com.hansung.shoppingmall.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemResponseDto> create(@RequestBody ItemRequestDto requestDto){
        ItemResponseDto itemResponseDto=itemService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAllItems(){
        List<ItemResponseDto> allItems = itemService.getAllItems();
        return ResponseEntity.ok(allItems);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDto> getUserById(@PathVariable("itemId")Long id){
        ItemResponseDto itemById = itemService.getItemById(id);
        return ResponseEntity.ok(itemById);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable("itemId")Long id){
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemResponseDto> update(@PathVariable("itemId")Long id, @RequestBody ItemRequestDto requestDto){
        ItemResponseDto itemResponseDto=itemService.update(id,requestDto);
        return ResponseEntity.ok(itemResponseDto);
    }


}
