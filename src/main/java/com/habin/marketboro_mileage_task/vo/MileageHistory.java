package com.habin.marketboro_mileage_task.vo;

import com.habin.marketboro_mileage_task.entity.Mileage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentLinkedQueue;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileageHistory {

    private String memberNo;
    private ConcurrentLinkedQueue<Mileage> mileage;

}
