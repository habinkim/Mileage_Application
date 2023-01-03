package com.habin.mileage.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MileageStatus {

    SAVE("적립"), USE("사용"), DEDUCTED("차감"), EXPIRED("만료");

    private String name;

}
