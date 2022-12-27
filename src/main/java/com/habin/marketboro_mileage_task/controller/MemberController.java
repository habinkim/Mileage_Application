package com.habin.marketboro_mileage_task.controller;

import com.habin.marketboro_mileage_task.common.ApiResponse;
import com.habin.marketboro_mileage_task.dto.SignUpRequestDto;
import com.habin.marketboro_mileage_task.entity.Member;
import com.habin.marketboro_mileage_task.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "회원가입 API")
    @PutMapping("/signUp")
    public ResponseEntity<ApiResponse<Member>> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return memberService.signUp(signUpRequestDto);
    }

}
