package com.habin.mileage.mileage_event.repository;

import com.habin.mileage.common.cache.SerializablePage;
import com.habin.mileage.common.dto.MileageListRequestDto;
import com.habin.mileage.mileage_event.dto.MileageEventListResponseDto;

public interface QMileageEventRepository {

    SerializablePage<MileageEventListResponseDto> listWithPaging(MileageListRequestDto dto);

}
