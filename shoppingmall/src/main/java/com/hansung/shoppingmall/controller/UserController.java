package com.hansung.shoppingmall.controller;

import com.hansung.shoppingmall.dto.UserRequestDto;
import com.hansung.shoppingmall.dto.UserResponseDto;
import com.hansung.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    //응답 예시
    /**
     * {
     *     "success": "회원가입이 완료되었습니다."
     * }
     * 
     * 실패시
     * {
     *     "error": "이미 존재하는 ID입니다."
     * }
     */
    @PostMapping("/api/user") // 유저 생성(회원가입)
    public ResponseEntity<?> create(@RequestBody UserRequestDto requestDto){
        try {
            userService.createUser(requestDto);
            return ResponseEntity.ok().body(Map.of("success","회원가입이 완료되었습니다."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }


    //응답 예시
    /**
     * {
     *     "userId": "ajk1330",
     *     "password": "1234",
     *     "username": "영호",
     *     "email": "ajk6068@gmail.com",
     *     "phoneNumber": "01067687688"
     * }
     */
    @GetMapping("/api/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId")Long id){
        try{
            return ResponseEntity.ok().body(userService.getUser(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    // 유저 정보 수정

    /**
     * {
     *     "success": "사용자 업데이트 완료"
     * }
     */
    @PutMapping("/api/user/{userId}")
    public ResponseEntity<?> update(@PathVariable("userId")Long id, @RequestBody UserRequestDto requestDto){
        try {
            userService.update(id, requestDto);
            return ResponseEntity.ok().body(Map.of("success", "사용자 업데이트 완료"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // 유저 삭제
    /**
     * {
     *     "success": "사용자가 삭제되었습니다."
     * }
     */
    @DeleteMapping("/api/user/{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId")Long id){
        try {
            userService.delete(id);
            return ResponseEntity.ok().body(Map.of("success", "사용자가 삭제되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }


}
