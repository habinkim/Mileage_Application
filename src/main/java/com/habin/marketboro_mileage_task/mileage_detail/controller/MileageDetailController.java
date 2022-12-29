package com.habin.marketboro_mileage_task.mileage_detail.controller;

import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.common.dto.ApiResponse;
import com.habin.marketboro_mileage_task.common.dto.MileageListRequestDto;
import com.habin.marketboro_mileage_task.mileage_detail.dto.MileageDetailListResponseDto;
import com.habin.marketboro_mileage_task.mileage_detail.service.MileageDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mileage/detail")
public class MileageDetailController {

    private final MileageDetailService mileageDetailService;

    @GetMapping
    public ResponseEntity<ApiResponse<SerializablePage<MileageDetailListResponseDto>>> getMileageDetailList
            (@Valid @ModelAttribute MileageListRequestDto mileageListRequestDto) {
        return mileageDetailService.getMileageDetailList(mileageListRequestDto);
    }

}
