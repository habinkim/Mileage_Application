package com.habin.marketboro_mileage_task.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.habin.marketboro_mileage_task.entity.enums.MileageStatus;
import com.habin.marketboro_mileage_task.module.json.CustomLocalDateTimeDeserializer;
import com.habin.marketboro_mileage_task.module.json.CustomLocalDateTimeSerializer;
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
public class MileageListResponseDto implements Serializable {

    private String mileageEventId;
    private MileageStatus mileageStatus;
    private Integer sum;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime transactionDtm;

}
