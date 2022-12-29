package com.habin.marketboro_mileage_task.mileage_event.repository;

import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.common.dto.MileageListRequestDto;
import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventListResponseDto;

public interface QMileageEventRepository {

    SerializablePage<MileageEventListResponseDto> listWithPaging(MileageListRequestDto dto);

}
