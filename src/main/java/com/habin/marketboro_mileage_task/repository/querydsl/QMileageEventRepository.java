package com.habin.marketboro_mileage_task.repository.querydsl;

import com.habin.marketboro_mileage_task.cache.SerializablePage;
import com.habin.marketboro_mileage_task.dto.MileageListResponseDto;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.enums.MileageStatus;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface QMileageEventRepository {

    List<TotalMileageResponseDto> total(String memberNo);

    SerializablePage<MileageListResponseDto> listWithPaging(String memberNo, MileageStatus mileageStatus, PageRequest pageRequest);

    ConcurrentLinkedQueue<MileageListResponseDto> queue(String memberNo, MileageStatus mileageStatus);
}
