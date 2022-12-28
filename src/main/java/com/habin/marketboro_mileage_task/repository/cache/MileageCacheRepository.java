package com.habin.marketboro_mileage_task.repository.cache;

import com.habin.marketboro_mileage_task.dto.MileageListResponseDto;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.enums.MileageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MileageCacheRepository {

    private final RedisTemplate<String, List<TotalMileageResponseDto>> totalMileageRedisTemplate;
    private final RedisTemplate<String, Page<MileageListResponseDto>> mileageListRedisTemplate;
    private final RedisTemplate<String, ConcurrentLinkedQueue<MileageListResponseDto>> mileageListQueueRedisTemplate;
    private final static Duration MILEAGE_CACHE_TTL = Duration.ofDays(3);

    public String getKey(String memberNo, CacheKeyType cacheKeyType) {
        return cacheKeyType.getPrefix() + memberNo;
    }
    public void setTotalMileage(String memberNo, List<TotalMileageResponseDto> totalMileageResponseDtos) {
        String key = getKey(memberNo, CacheKeyType.TOTAL_MILEAGE);
        log.info("Set TotalMileage to Redis {}:{}", key, totalMileageResponseDtos);
        totalMileageRedisTemplate.opsForValue().set(key, totalMileageResponseDtos, MILEAGE_CACHE_TTL);
    }

    public Optional<List<TotalMileageResponseDto>> getTotalMileage(String memberNo) {
        String key = getKey(memberNo, CacheKeyType.TOTAL_MILEAGE);
        List<TotalMileageResponseDto> totalMileageResponseDtos = totalMileageRedisTemplate.opsForValue().get(key);
        log.info("Get TotalMileage from Redis {}:{}", key, totalMileageResponseDtos);
        return Optional.ofNullable(totalMileageResponseDtos);
    }

    public void setMileageList(String memberNo, MileageType mileageType, Integer page, Integer size, Page<MileageListResponseDto> mileageListResponseDtos) {
        String key = getKey(memberNo, CacheKeyType.MILEAGE_LIST);
        log.info("Set mileageList to Redis {}:{}", key, mileageListResponseDtos);
        mileageListRedisTemplate.opsForValue().set(key, mileageListResponseDtos, MILEAGE_CACHE_TTL);
    }

    public Optional<Page<MileageListResponseDto>> getMileageList(String memberNo, MileageType mileageType, Integer page, Integer size) {
        String key = getKey(memberNo, CacheKeyType.MILEAGE_LIST);
        Page<MileageListResponseDto> mileageListResponseDtos = mileageListRedisTemplate.opsForValue().get(key);
        log.info("Get mileageList from Redis {}:{}", key, mileageListResponseDtos);
        return Optional.ofNullable(mileageListResponseDtos);
    }

    public void setMileageQueue(String memberNo, ConcurrentLinkedQueue<MileageListResponseDto> mileageQueue) {
        String key = getKey(memberNo, CacheKeyType.MILEAGE_QUEUE);
        log.info("Set mileageQueue to Redis {}:{}", key, mileageQueue);
        mileageListQueueRedisTemplate.opsForValue().set(key, mileageQueue, MILEAGE_CACHE_TTL);
    }

    public Optional<ConcurrentLinkedQueue<MileageListResponseDto>> getMileageQueue(String memberNo) {
        String key = getKey(memberNo, CacheKeyType.MILEAGE_QUEUE);
        ConcurrentLinkedQueue<MileageListResponseDto> mileageQueue = mileageListQueueRedisTemplate.opsForValue().get(key);
        log.info("Get mileageQueue from Redis {}:{}", key, mileageQueue);
        return Optional.ofNullable(mileageQueue);
    }

    public void deleteMileage(String memberNo) {
        totalMileageRedisTemplate.delete(getKey(memberNo, CacheKeyType.TOTAL_MILEAGE));
        mileageListRedisTemplate.delete(getKey(memberNo, CacheKeyType.MILEAGE_LIST));
        mileageListQueueRedisTemplate.delete(getKey(memberNo, CacheKeyType.MILEAGE_QUEUE));
        log.info("Delete from Redis {}", memberNo);
    }

}
