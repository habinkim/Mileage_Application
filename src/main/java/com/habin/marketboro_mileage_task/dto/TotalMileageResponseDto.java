package com.habin.marketboro_mileage_task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TotalMileageResponseDto {

    private String memberNo;
    private String memberNm;
    private String totalMileageAmount;

}
