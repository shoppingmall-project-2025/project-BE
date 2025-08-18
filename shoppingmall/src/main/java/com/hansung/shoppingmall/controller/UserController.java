package com.hansung.shoppingmall.controller;

import com.hansung.shoppingmall.dto.UserRequestDto;
import com.hansung.shoppingmall.dto.UserResponseDto;
import com.hansung.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
//장바구니, Order, 스크롤 페이징
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserRequestDto requestDto){
        UserResponseDto userResponseDto = userService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto); // 상태코드 200
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers()); // 상태코드 200
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId")Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId")Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build(); // 상태코드 204
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable("userId")Long id, @RequestBody UserRequestDto requestDto){
        UserResponseDto userUpdatedUser=userService.update(id,requestDto);
        return ResponseEntity.ok(userUpdatedUser);
    }

}
