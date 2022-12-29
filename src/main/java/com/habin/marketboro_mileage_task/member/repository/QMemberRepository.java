package com.habin.marketboro_mileage_task.member.repository;


import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.member.dto.MileageAmountResponseDto;

public interface QMemberRepository {
    MileageAmountResponseDto getMileageAmount(String memberNo);
    SerializablePage<MileageAmountResponseDto> getMileageAmountList(Integer page, Integer size);
}
