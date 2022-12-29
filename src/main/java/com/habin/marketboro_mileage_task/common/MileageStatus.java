package com.habin.marketboro_mileage_task.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MileageStatus {

    SAVE("적립"), USED("사용"), DEDUCTED("차감"), EXPIRED("만료");

    private String name;

}
