package com.habin.mileage.mileage_event.controller;

import com.habin.mileage.common.cache.SerializablePage;
import com.habin.mileage.common.dto.ApiResponse;
import com.habin.mileage.common.dto.MileageListRequestDto;
import com.habin.mileage.mileage_event.dto.MileageEventCreateRequestDto;
import com.habin.mileage.mileage_event.dto.MileageEventListResponseDto;
import com.habin.mileage.mileage_event.service.MileageEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    @Operation(summary = "회원별 적립금 이벤트 내역 조회", description = "회원별 적립금 이벤트 내역 조회 API")
    @GetMapping
    public ResponseEntity<ApiResponse<SerializablePage<MileageEventListResponseDto>>> getMileageEventList(
            @Valid @ModelAttribute MileageListRequestDto dto) {
        return mileageEventService.getMileageEventList(dto);
    }

    @Tag(name = "적립금 이벤트 관리", description = "적립금 이벤트 관리 API")
    @Operation(summary = "적립금 이벤트 발생", description = "적립금 이벤트 발생 API")
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveMileage(@Valid @RequestBody MileageEventCreateRequestDto dto) {
        return mileageEventService.saveMileageEvent(dto);
    }

}
