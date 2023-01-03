package com.habin.mileage.mileage_event.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.habin.mileage.common.MileageStatus;
import com.habin.mileage.common.json.CustomLocalDateTimeDeserializer;
import com.habin.mileage.common.json.CustomLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileageEventListResponseDto implements Serializable {

    /**
     * 적립금 이벤트 아이디
     */
    private String mileageEventId;

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
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime transactionDtm;

}
