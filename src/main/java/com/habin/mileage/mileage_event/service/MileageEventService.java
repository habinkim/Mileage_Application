package com.habin.mileage.mileage_event.service;

import com.habin.mileage.common.cache.SerializablePage;
import com.habin.mileage.common.dto.ApiResponse;
import com.habin.mileage.common.dto.MileageListRequestDto;
import com.habin.mileage.member.service.MemberService;
import com.habin.mileage.mileage_detail.service.MileageDetailService;
import com.habin.mileage.mileage_event.dto.MileageEventCreateRequestDto;
import com.habin.mileage.mileage_event.dto.MileageEventListResponseDto;
import com.habin.mileage.mileage_event.entity.MileageEvent;
import com.habin.mileage.mileage_event.mapper.MileageEventMapper;
import com.habin.mileage.mileage_event.repository.MileageEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MileageEventService {

    private final MileageEventRepository mileageEventRepository;
    private final MileageEventMapper mileageEventMapper;
    private final MemberService memberService;
    private final MileageDetailService mileageDetailService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<SerializablePage<MileageEventListResponseDto>>> getMileageEventList
            (MileageListRequestDto dto) {
        SerializablePage<MileageEventListResponseDto> list = mileageEventRepository
                .listWithPaging(dto);
        return ApiResponse.success(list);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(key = "#dto.memberNo", value = {"mileageAmount", "mielageAmountList"}),
            @CacheEvict(value = "mileageEventList", allEntries = true)
    })
    public ResponseEntity<ApiResponse<Object>> saveMileageEvent(MileageEventCreateRequestDto dto) {
        MileageEvent mileageEvent = mileageEventMapper.dtoToEntity(dto);
        mileageEventRepository.save(mileageEvent);

        memberService.updateMileageAmount(dto.getMemberNo(), dto.getMileageStatus(), dto.getAmount());

        return ApiResponse.success();
    }

}
