package com.habin.mileage.mileage_detail.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.habin.mileage.common.MileageStatus;
import com.habin.mileage.common.json.CustomLocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileageDetailCreateDto {

    /**
     * 적립금 마일리지 상세 아이디
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
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime transactionDtm;

    /**
     * 잔여 적립금 만료 일시
     */
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime remainMileageExpireDtm;

}
