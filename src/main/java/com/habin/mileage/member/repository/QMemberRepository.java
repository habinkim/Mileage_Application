package com.habin.mileage.member.repository;


import com.habin.mileage.common.cache.SerializablePage;
import com.habin.mileage.common.dto.PageRequestDto;
import com.habin.mileage.member.dto.MileageAmountResponseDto;

public interface QMemberRepository {
    MileageAmountResponseDto getMileageAmount(String memberNo);
    SerializablePage<MileageAmountResponseDto> getMileageAmountList(PageRequestDto pageRequestDto);
    void updateTotalAmount(Integer currentTotalAmount);
}
