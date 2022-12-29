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

    /**
     * 회원 번호
     */
    private String memberNo;

    /**
     * 회원명
     */
    private String memberNm;

    /**
     * 적립금 총액
     */
    private Integer totalAmount;

}
