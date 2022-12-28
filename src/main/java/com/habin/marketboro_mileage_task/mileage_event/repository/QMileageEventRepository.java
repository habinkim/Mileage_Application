package com.habin.marketboro_mileage_task.mileage_event.repository;

import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.enums.MileageStatus;
import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventListResponseDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface QMileageEventRepository {

    List<TotalMileageResponseDto> total(String memberNo);

    SerializablePage<MileageEventListResponseDto> listWithPaging(String memberNo, MileageStatus mileageStatus, PageRequest pageRequest);

    ConcurrentLinkedQueue<MileageEventListResponseDto> queue(String memberNo, MileageStatus mileageStatus);
}
