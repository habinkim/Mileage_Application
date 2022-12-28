package com.habin.marketboro_mileage_task.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habin.marketboro_mileage_task.cache.SerializablePage;
import com.habin.marketboro_mileage_task.common.ApiResponse;
import com.habin.marketboro_mileage_task.dto.MileageListResponseDto;
import com.habin.marketboro_mileage_task.dto.MileageRequestDto;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.Mileage;
import com.habin.marketboro_mileage_task.entity.MileageType;
import com.habin.marketboro_mileage_task.module.mapper.MileageMapper;
import com.habin.marketboro_mileage_task.repository.MemberRepository;
import com.habin.marketboro_mileage_task.repository.MileageRepository;
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
    private final MileageRepository mileageRepository;
    private final MileageMapper mileageMapper;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<List<TotalMileageResponseDto>>> getTotalMileage(String memberNo) {
        List<TotalMileageResponseDto> total = mileageRepository.total(memberNo);
        return ApiResponse.success(total);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<SerializablePage<MileageListResponseDto>>> getMileageList(String memberNo, MileageType mileageType, Integer page, Integer size) {
        SerializablePage<MileageListResponseDto> list = mileageRepository.listWithPaging(memberNo, mileageType, PageRequest.of(page - 1, size));
        return ApiResponse.success(list);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(key = "#mileageRequestDto.memberNo", value = {"totalMileage"}),
            @CacheEvict(value = "mileageList", allEntries = true)
    })
    public ResponseEntity<ApiResponse<Object>> saveMileage(MileageRequestDto mileageRequestDto) {
        Mileage mileage = mileageMapper.mileageSaveDtoToEntity(mileageRequestDto, MileageType.SAVE);
        mileageRepository.save(mileage);
//        mileageCacheRepository.deleteMileage(mileageRequestDto.memberNo());
        return ApiResponse.success();
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(key = "#mileageRequestDto.memberNo", value = {"totalMileage"}),
            @CacheEvict(value = "mileageList", allEntries = true)
    })
    public ResponseEntity<ApiResponse<Object>> useMileage(MileageRequestDto mileageRequestDto) throws JsonProcessingException {
        Mileage mileage = mileageMapper.mileageSaveDtoToEntity(mileageRequestDto, MileageType.CONSUMPTION);
        mileageRepository.save(mileage);

        ConcurrentLinkedQueue<MileageListResponseDto> queue = mileageRepository.queue(mileageRequestDto.memberNo(), MileageType.SAVE);
        AtomicInteger sum = new AtomicInteger(mileageRequestDto.sum());

        ConcurrentLinkedQueue<MileageListResponseDto> collect = queue.stream().dropWhile(m -> {
                    sum.updateAndGet(v -> v - m.getSum());
                    return sum.get() > 0;
                })
                .collect(ConcurrentLinkedQueue::new, ConcurrentLinkedQueue::add, ConcurrentLinkedQueue::addAll);

        log.info("updated queue: {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(collect));

        return ApiResponse.success();
    }

    @Transactional
    @CacheEvict(key = "#mileageRequestDto.memberNo", value = {"mileage", "totalMileage"})
    public ResponseEntity<ApiResponse<?>> cancelUseMileage(MileageRequestDto mileageRequestDto) {
        return null;
    }
}
