package com.habin.marketboro_mileage_task.repository.querydsl;

import com.habin.marketboro_mileage_task.entity.MileageEvent;

import java.util.List;

public interface QMileageDetailRepository {

    List<MileageEvent> availableMileageEvent(String memberNo);

}
