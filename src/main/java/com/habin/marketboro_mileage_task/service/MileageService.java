package com.habin.marketboro_mileage_task.service;

import com.habin.marketboro_mileage_task.common.ApiResponse;
import com.habin.marketboro_mileage_task.dto.MileageListResponseDto;
import com.habin.marketboro_mileage_task.dto.MileageSaveRequestDto;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.Mileage;
import com.habin.marketboro_mileage_task.entity.enums.MileageType;
import com.habin.marketboro_mileage_task.module.mapper.MileageMapper;
import com.habin.marketboro_mileage_task.repository.MemberRepository;
import com.habin.marketboro_mileage_task.repository.MileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MileageService {

    private final MemberRepository memberRepository;
    private final MileageRepository mileageRepository;

    private final MileageMapper mileageMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<List<TotalMileageResponseDto>>> getTotalMileage(String memberNo) {
        List<TotalMileageResponseDto> total = mileageRepository.total(memberNo);
        return ApiResponse.success(total);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<Page<MileageListResponseDto>>> getMileageList(String memberNo, MileageType mileageType, Integer page, Integer size) {
        Page<MileageListResponseDto> list = mileageRepository.list(memberNo, mileageType, PageRequest.of(page - 1, size, Sort.Direction.DESC));
        return ApiResponse.success(list);
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> saveMileage(MileageSaveRequestDto mileageSaveRequestDto) {
        Mileage mileage = mileageMapper.mileageSaveDtoToEntity(mileageSaveRequestDto);
        mileageRepository.save(mileage);
        return ApiResponse.success();
    }
}
