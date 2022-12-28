package com.habin.marketboro_mileage_task.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habin.marketboro_mileage_task.common.ApiResponse;
import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.dto.CancelMileageRequestDto;
import com.habin.marketboro_mileage_task.dto.MileageRequestDto;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.enums.MileageStatus;
import com.habin.marketboro_mileage_task.member.repository.MemberRepository;
import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventListResponseDto;
import com.habin.marketboro_mileage_task.mileage_event.entity.MileageEvent;
import com.habin.marketboro_mileage_task.mileage_event.mapper.MileageEventMapper;
import com.habin.marketboro_mileage_task.mileage_event.repository.MileageEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MileageService {

    private final MemberRepository memberRepository;
    private final MileageEventRepository mileageEventRepository;
    private final MileageEventMapper mileageEventMapper;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<List<TotalMileageResponseDto>>> getTotalMileage(String memberNo) {
        List<TotalMileageResponseDto> total = mileageEventRepository.total(memberNo);
        return ApiResponse.success(total);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<SerializablePage<MileageEventListResponseDto>>> getMileageEventList(String memberNo, MileageStatus mileageStatus, Integer page, Integer size) {
        SerializablePage<MileageEventListResponseDto> list = mileageEventRepository.listWithPaging(memberNo, mileageStatus, PageRequest.of(page - 1, size));
        return ApiResponse.success(list);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(key = "#mileageRequestDto.memberNo", value = {"totalMileage"}),
            @CacheEvict(value = "mileageList", allEntries = true)
    })
    public ResponseEntity<ApiResponse<Object>> saveMileage(MileageRequestDto mileageRequestDto) {
        MileageEvent mileageEvent = mileageEventMapper.mileageSaveDtoToEntity(mileageRequestDto, MileageStatus.SAVE);
        mileageEventRepository.save(mileageEvent);
        return ApiResponse.success();
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(key = "#mileageRequestDto.memberNo", value = {"totalMileage"}),
            @CacheEvict(value = "mileageList", allEntries = true)
    })
    public ResponseEntity<ApiResponse<Object>> useMileage(MileageRequestDto mileageRequestDto) throws JsonProcessingException {
        MileageEvent mileageEvent = mileageEventMapper.mileageSaveDtoToEntity(mileageRequestDto, MileageStatus.USED);
        mileageEventRepository.save(mileageEvent);

        ConcurrentLinkedQueue<MileageEventListResponseDto> queue = mileageEventRepository.queue(mileageRequestDto.memberNo(), MileageStatus.SAVE);
        AtomicInteger sum = new AtomicInteger(mileageRequestDto.sum());

        ConcurrentLinkedQueue<MileageEventListResponseDto> collect = queue.stream().dropWhile(m -> {
                    sum.updateAndGet(v -> v - m.getSum());
                    return sum.get() > 0;
                })
                .collect(ConcurrentLinkedQueue::new, ConcurrentLinkedQueue::add, ConcurrentLinkedQueue::addAll);

        log.info("updated queue: {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(collect));

        return ApiResponse.success();
    }

    @Transactional
    @CacheEvict(key = "#cancelMileageRequestDto.memberNo", value = {"mileage", "totalMileage"})
    public ResponseEntity<ApiResponse<?>> cancelUseMileage(CancelMileageRequestDto cancelMileageRequestDto) {
        return null;
    }
}
