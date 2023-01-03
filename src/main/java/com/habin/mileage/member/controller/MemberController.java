package com.habin.mileage.member.controller;

import com.habin.mileage.common.cache.SerializablePage;
import com.habin.mileage.common.dto.ApiResponse;
import com.habin.mileage.common.dto.PageRequestDto;
import com.habin.mileage.member.dto.MileageAmountResponseDto;
import com.habin.mileage.member.dto.SignUpRequestDto;
import com.habin.mileage.member.entity.Member;
import com.habin.mileage.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "회원", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;

    @Tag(name = "회원", description = "회원 관련 API")
    @Operation(summary = "회원가입", description = "회원가입 API")
    @PutMapping("/signUp")
    public ResponseEntity<ApiResponse<Member>> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return memberService.signUp(signUpRequestDto);
    }

    @Tag(name = "회원", description = "회원 관련 API")
    @Operation(summary = "회원별 적립금 합계 단건 조회", description = "회원별 적립금 합계 단건 조회 API")
    @GetMapping("/mileage/amount/{memberNo}")
    public ResponseEntity<ApiResponse<MileageAmountResponseDto>> getMileageAmount(@PathVariable String memberNo) {
        return memberService.getMileageAmount(memberNo);
    }

    @Tag(name = "회원", description = "회원 관련 API")
    @Operation(summary = "회원별 적립금 합계 페이징 리스트 조회", description = "회원별 적립금 합계 페이징 리스트 조회 API")
    @GetMapping("/mileage/amount")
    public ResponseEntity<ApiResponse<SerializablePage<MileageAmountResponseDto>>> getAccumulatedMileageList
            (@Valid @ModelAttribute PageRequestDto pageRequestDto) {
        return memberService.getMileageAmountList(pageRequestDto);
    }

}
