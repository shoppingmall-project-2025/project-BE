package com.hansung.shoppingmall.service;

import com.hansung.shoppingmall.dto.ItemRequestDto;
import com.hansung.shoppingmall.dto.ItemResponseDto;
import com.hansung.shoppingmall.dto.UserRequestDto;
import com.hansung.shoppingmall.entity.Item;
import com.hansung.shoppingmall.repository.ItemRepository;
import com.hansung.shoppingmall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemResponseDto save(ItemRequestDto dto){
        Item item = new Item();
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setImage(dto.getImage());
        item.setDescription(dto.getDescription());

        Item savedItem=itemRepository.save(item);
        return new ItemResponseDto(savedItem);
    }

    public List<ItemResponseDto> getAllItems(){
        return itemRepository.findAll().stream()
                .map(ItemResponseDto::new)
                .toList();
    }

    public ItemResponseDto getItemById(Long id){
        Item item = itemRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("no Item"));
        return new ItemResponseDto(item);
    }

    public void delete(Long id){
        if(!itemRepository.existsById(id)){
            throw new NoSuchElementException("no Item");
        }
        itemRepository.deleteById(id);
    }

    public ItemResponseDto update(Long id, ItemRequestDto requestDto){
        Item item=itemRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("no Item"));
        item.setName(requestDto.getName());
        item.setPrice(requestDto.getPrice());
        item.setImage(requestDto.getImage());
        item.setDescription(requestDto.getDescription());

        Item updatedItem = itemRepository.save(item);
        return new ItemResponseDto(updatedItem);
    }
}
