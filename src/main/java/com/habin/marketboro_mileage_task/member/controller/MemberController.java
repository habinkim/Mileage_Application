package com.habin.marketboro_mileage_task.member.controller;

import com.habin.marketboro_mileage_task.common.ApiResponse;
import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.member.dto.MileageAmountResponseDto;
import com.habin.marketboro_mileage_task.member.dto.SignUpRequestDto;
import com.habin.marketboro_mileage_task.member.entity.Member;
import com.habin.marketboro_mileage_task.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @Tag(name = "회원", description = "회원 관련 API")
    @Operation(summary = "회원별 적립금 합계 단건 조회", description = "회원별 적립금 합계 단건 조회 API")
    @GetMapping("/amount/{memberNo}")
    public ResponseEntity<ApiResponse<MileageAmountResponseDto>> getMileageAmount(@PathVariable String memberNo) {
        return memberService.getMileageAmount(memberNo);
    }

    @Tag(name = "회원", description = "회원 관련 API")
    @Operation(summary = "회원별 적립금 합계 페이징 리스트 조회", description = "회원별 적립금 합계 페이징 리스트 조회 API")
    @GetMapping("/amount")
    public ResponseEntity<ApiResponse<SerializablePage<MileageAmountResponseDto>>> getAccumulatedMileageList(
            @RequestParam(name = "page", required = false)
            @Min(message = "페이지 번호는 1 이상으로 입력해주세요.", value = 1)
            @Max(message = "페이지 번호 최댓값을 초과했습니다.", value = Integer.MAX_VALUE)
            Integer page,
            @RequestParam(name = "size", required = false)
            @Min(message = "페이지 크기는 10 이상으로 입력해주세요.", value = 10)
            @Max(message = "페이지 크기 최댓값을 초과했습니다.", value = Integer.MAX_VALUE)
            Integer size
    ) {
        return memberService.getMileageAmountList(page, size);
    }

}
