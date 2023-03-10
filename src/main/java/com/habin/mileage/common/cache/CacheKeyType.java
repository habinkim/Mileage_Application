package com.habin.mileage.common.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CacheKeyType {

    TOTAL_MILEAGE("TOTAL_MILEAGE"), MILEAGE_LIST("MILEAGE_LIST"), MILEAGE_QUEUE("MILEAGE_QUEUE");

    private String prefix;

}
