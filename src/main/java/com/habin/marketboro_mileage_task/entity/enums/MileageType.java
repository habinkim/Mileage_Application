package com.habin.marketboro_mileage_task.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MileageType {

    SAVE("적립"), USED("사용"), DEDUCTED("차감"), EXPIRED("만료");

    private String name;

}
