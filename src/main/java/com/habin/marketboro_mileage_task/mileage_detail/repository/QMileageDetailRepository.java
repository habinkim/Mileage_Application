package com.habin.marketboro_mileage_task.mileage_detail.repository;

import com.habin.marketboro_mileage_task.mileage_event.entity.MileageEvent;

import java.util.List;

public interface QMileageDetailRepository {

    List<MileageEvent> availableMileageEvent(String memberNo);

}
