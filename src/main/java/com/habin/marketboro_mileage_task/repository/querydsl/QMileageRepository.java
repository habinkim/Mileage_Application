package com.habin.marketboro_mileage_task.repository.querydsl;

import com.habin.marketboro_mileage_task.dto.MileageListResponseDto;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.enums.MileageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface QMileageRepository {
    List<TotalMileageResponseDto> total(String memberNo);

    Page<MileageListResponseDto> listWithPaging(String memberNo, MileageType mileageType, PageRequest pageRequest);

    ConcurrentLinkedQueue<MileageListResponseDto> queue(String memberNo, MileageType mileageType);
}
