package com.habin.marketboro_mileage_task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.habin.marketboro_mileage_task.common.ApiResponse;
import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.dto.CancelMileageRequestDto;
import com.habin.marketboro_mileage_task.dto.MileageRequestDto;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.enums.MileageStatus;
import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventListResponseDto;
import com.habin.marketboro_mileage_task.service.MileageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "적립금 관리", description = "적립금 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/mileage")
public class MileageController {

    private final MileageService mileageService;

    @Tag(name = "적립금 관리", description = "적립금 관리 API")
    @Operation(summary = "회원별 적립금 합계 조회", description = "회원별 적립금 합계 조회 API")
    @GetMapping("/total")
    public ResponseEntity<ApiResponse<List<TotalMileageResponseDto>>> getTotalMileage(@RequestParam(name = "memberNo", required = false) String memberNo) {
        return mileageService.getTotalMileage(memberNo);
    }

    @Tag(name = "적립금 관리", description = "적립금 관리 API")
    @Operation(summary = "회원별 적립금 적립/사용 내역 조회", description = "회원별 적립금 적립/사용 내역 조회 API")
    @GetMapping("/event")
    public ResponseEntity<ApiResponse<SerializablePage<MileageEventListResponseDto>>> getMileageEventList(
            @RequestParam(name = "memberNo", required = false) String memberNo,
            @RequestParam(name = "type", required = false) MileageStatus mileageStatus,
            @RequestParam(name = "page", required = false)
            @Min(message = "페이지 번호는 1 이상으로 입력해주세요.", value = 1)
            @Max(message = "페이지 번호 최댓값을 초과했습니다.", value = Integer.MAX_VALUE)
            Integer page,
            @RequestParam(name = "size", required = false)
            @Min(message = "페이지 크기는 10 이상으로 입력해주세요.", value = 10)
            @Max(message = "페이지 크기 최댓값을 초과했습니다.", value = Integer.MAX_VALUE)
            Integer size) {
        return mileageService.getMileageEventList(memberNo, mileageStatus, page, size);
    }

    @Tag(name = "적립금 관리", description = "적립금 관리 API")
    @Operation(summary = "적립금 적립", description = "적립금 적립 API")
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveMileage(@Valid @RequestBody MileageRequestDto mileageRequestDto) {
        return mileageService.saveMileage(mileageRequestDto);
    }

    @Tag(name = "적립금 관리", description = "적립금 관리 API")
    @Operation(summary = "적립금 사용", description = "적립금 사용 API")
    @PatchMapping
    public ResponseEntity<ApiResponse<Object>> useMileage(@Valid @RequestBody MileageRequestDto mileageRequestDto) throws JsonProcessingException {
        return mileageService.useMileage(mileageRequestDto);
    }

    @Tag(name = "적립금 관리", description = "적립금 관리 API")
    @Operation(summary = "적립금 사용 취소", description = "적립금 사용 취소 API")
    @PatchMapping("/cancel")
    public ResponseEntity<ApiResponse<?>> cancelUseMileage(@Valid @RequestBody CancelMileageRequestDto cancelMileageRequestDto) {
        return mileageService.cancelUseMileage(cancelMileageRequestDto);
    }

}
