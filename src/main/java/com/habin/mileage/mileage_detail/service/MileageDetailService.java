package com.habin.mileage.mileage_detail.service;

import com.habin.mileage.common.cache.SerializablePage;
import com.habin.mileage.common.dto.ApiResponse;
import com.habin.mileage.common.dto.MileageListRequestDto;
import com.habin.mileage.mileage_detail.dto.MileageDetailListResponseDto;
import com.habin.mileage.mileage_detail.repository.MileageDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MileageDetailService {

    private final MileageDetailRepository mileageDetailRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<SerializablePage<MileageDetailListResponseDto>>> getMileageDetailList
            (MileageListRequestDto mileageListRequestDto) {
        SerializablePage<MileageDetailListResponseDto> list = mileageDetailRepository.getMileageDetailList(mileageListRequestDto);
        return ApiResponse.success(list);
    }

}
