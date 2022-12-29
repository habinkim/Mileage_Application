package com.habin.marketboro_mileage_task.mileage_event.service;

import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.common.dto.ApiResponse;
import com.habin.marketboro_mileage_task.common.dto.MileageListRequestDto;
import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventListResponseDto;
import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventRequestDto;
import com.habin.marketboro_mileage_task.mileage_event.entity.MileageEvent;
import com.habin.marketboro_mileage_task.mileage_event.mapper.MileageEventMapper;
import com.habin.marketboro_mileage_task.mileage_event.repository.MileageEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MileageEventService {

    private final MileageEventRepository mileageEventRepository;
    private final MileageEventMapper mileageEventMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<SerializablePage<MileageEventListResponseDto>>> getMileageEventList
            (MileageListRequestDto dto) {
        SerializablePage<MileageEventListResponseDto> list = mileageEventRepository
                .listWithPaging(dto);
        return ApiResponse.success(list);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(key = "#mileageEventRequestDto.memberNo", value = {"mileageAmount", "mielageAmountList"}),
            @CacheEvict(value = "mileageEventList", allEntries = true)
    })
    public ResponseEntity<ApiResponse<Object>> saveMileage(MileageEventRequestDto mileageEventRequestDto) {
        MileageEvent mileageEvent = mileageEventMapper.mileageSaveDtoToEntity(mileageEventRequestDto);
        mileageEventRepository.save(mileageEvent);
        return ApiResponse.success();
    }

}
