package com.habin.marketboro_mileage_task.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileageAmountResponseDto {

    private String memberNo;

    private String memberNm;

    private Integer totalAmount;

}
