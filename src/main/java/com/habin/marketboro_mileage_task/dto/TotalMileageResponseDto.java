package com.habin.marketboro_mileage_task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TotalMileageResponseDto implements Serializable {

    private String memberNo;
    private String memberNm;
    private Integer totalMileageAmount;

}
