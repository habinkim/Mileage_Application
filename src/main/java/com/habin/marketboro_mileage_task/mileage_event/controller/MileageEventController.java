package com.habin.marketboro_mileage_task.mileage_event.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.habin.marketboro_mileage_task.common.ApiResponse;
import com.habin.marketboro_mileage_task.common.MileageStatus;
import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.dto.CancelMileageRequestDto;
import com.habin.marketboro_mileage_task.dto.MileageEventRequestDto;
import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventListResponseDto;
import com.habin.marketboro_mileage_task.mileage_event.service.MileageEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "적립금 이벤트 관리", description = "적립금 이벤트 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/mileage/event")
public class MileageEventController {

    private final MileageEventService mileageEventService;

    @Tag(name = "적립금 이벤트 관리", description = "적립금 이벤트 관리 API")
    @Operation(summary = "회원별 적립금 적립/사용 내역 조회", description = "회원별 적립금 적립/사용 내역 조회 API")
    @GetMapping
    public ResponseEntity<ApiResponse<SerializablePage<MileageEventListResponseDto>>> getMileageEventList(
            @RequestParam(name = "memberNo", required = false) String memberNo,
            @RequestParam(name = "type", required = false) MileageStatus mileageStatus,
            @RequestParam(name = "page")
            @Min(message = "페이지 번호는 1 이상으로 입력해주세요.", value = 1)
            @Max(message = "페이지 번호 최댓값을 초과했습니다.", value = Integer.MAX_VALUE)
            Integer page,
            @RequestParam(name = "size")
            @Min(message = "페이지 크기는 10 이상으로 입력해주세요.", value = 10)
            @Max(message = "페이지 크기 최댓값을 초과했습니다.", value = Integer.MAX_VALUE)
            Integer size) {
        return mileageEventService.getMileageEventList(memberNo, mileageStatus, page, size);
    }

    @Tag(name = "적립금 이벤트 관리", description = "적립금 이벤트 관리 API")
    @Operation(summary = "적립금 적립 이벤트 발생", description = "적립금 적립 이벤트 발생 API")
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveMileage(@Valid @RequestBody MileageEventRequestDto mileageEventRequestDto) {
        return mileageEventService.saveMileage(mileageEventRequestDto);
    }

    @Tag(name = "적립금 이벤트 관리", description = "적립금 이벤트 관리 API")
    @Operation(summary = "적립금 사용 이벤트 발생", description = "적립금 사용 이벤트 발생 API")
    @PatchMapping
    public ResponseEntity<ApiResponse<Object>> useMileage(@Valid @RequestBody MileageEventRequestDto mileageEventRequestDto) throws JsonProcessingException {
        return mileageEventService.useMileage(mileageEventRequestDto);
    }

    @Tag(name = "적립금 이벤트 관리", description = "적립금 이벤트 관리 API")
    @Operation(summary = "적립금 사용 취소 이벤트 발생", description = "적립금 사용 취소 이벤트 발생 API")
    @PatchMapping("/cancel")
    public ResponseEntity<ApiResponse<?>> cancelUseMileage(@Valid @RequestBody CancelMileageRequestDto cancelMileageRequestDto) {
        return mileageEventService.cancelUseMileage(cancelMileageRequestDto);
    }


}
