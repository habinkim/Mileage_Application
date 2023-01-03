package com.habin.mileage.mileage_detail.repository;

import com.habin.mileage.common.cache.SerializablePage;
import com.habin.mileage.common.dto.MileageListRequestDto;
import com.habin.mileage.mileage_detail.dto.MileageDetailListResponseDto;

public interface QMileageDetailRepository {

    SerializablePage<MileageDetailListResponseDto> getMileageDetailList(MileageListRequestDto mileageListRequestDto);
}
