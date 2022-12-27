package com.habin.marketboro_mileage_task.controller;

import com.habin.marketboro_mileage_task.common.ApiResponse;
import com.habin.marketboro_mileage_task.dto.MileageListResponseDto;
import com.habin.marketboro_mileage_task.dto.MileageSaveRequestDto;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.enums.MileageType;
import com.habin.marketboro_mileage_task.service.MileageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @GetMapping("/total/{memberNo}")
    public ResponseEntity<ApiResponse<List<TotalMileageResponseDto>>> getTotalMileage(@PathVariable(required = false) String memberNo) {
        return mileageService.getTotalMileage(memberNo);
    }

    @Tag(name = "적립급 관리", description = "적립금 관리 API")
    @Operation(summary = "회원별 적립금 적립/사용 내역 조회", description = "회원별 적립금 적립/사용 내역 조회 API")
    @GetMapping("/{memberNo}")
    public ResponseEntity<ApiResponse<Page<MileageListResponseDto>>> getMileageList(
            @PathVariable(required = false) String memberNo,
            @RequestParam(name = "type", required = false) MileageType mileageType,
            @RequestParam(required = false)
            @Min(message = "페이지 번호는 1 이상으로 입력해주세요.", value = 1)
            @Max(message = "페이지 번호 최댓값을 초과했습니다.", value = Integer.MAX_VALUE)
            Integer page,
            @RequestParam(required = false)
            @Min(message = "페이지 크기는 10 이상으로 입력해주세요.", value = 10)
            @Max(message = "페이지 크기 최댓값을 초과했습니다.", value = Integer.MAX_VALUE)
            Integer size) {
        return mileageService.getMileageList(memberNo, mileageType, page, size);
    }

    @Tag(name = "적립금 관리", description = "적립금 관리 API")
    @Operation(summary = "적립금 적립", description = "적립금 적립 API")
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveMileage(@Valid @RequestBody MileageSaveRequestDto mileageSaveRequestDto) {
        return mileageService.saveMileage(mileageSaveRequestDto);
    }

}
