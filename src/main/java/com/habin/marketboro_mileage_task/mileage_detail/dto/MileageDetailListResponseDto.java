package com.habin.marketboro_mileage_task.mileage_detail.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.habin.marketboro_mileage_task.common.MileageStatus;
import com.habin.marketboro_mileage_task.common.json.CustomLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileageDetailListResponseDto {

    /**
     * 적립금 상세 아이디
     */
    private String mileageDetailId;

    /**
     * 적립금 이벤트 아이디
     */
    private String mileageEventId;

    /**
     * 취소 원본 상세 아이디
     */
    private String cancelMileageDetailId;

    /**
     * 적립 원본 상세 아이디
     */
    private String saveMileageDetailId;

    /**
     * 회원 번호
     */
    private String memberNo;

    /**
     * 회원명
     */
    private String memberNm;

    /**
     * 거래 상태
     */
    private MileageStatus mileageStatus;

    /**
     * 거래 금액
     */
    private Integer amount;

    /**
     * 거래 일시
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime transactionDtm;

    /**
     * 잔여 적립금 만료 일시
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime remainMileageExpireDtm;
}
