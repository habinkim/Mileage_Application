package com.habin.marketboro_mileage_task.mileage_detail.repository;

import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.common.dto.MileageListRequestDto;
import com.habin.marketboro_mileage_task.mileage_detail.dto.MileageDetailListResponseDto;

public interface QMileageDetailRepository {

    SerializablePage<MileageDetailListResponseDto> getMileageDetailList(MileageListRequestDto mileageListRequestDto);
}
