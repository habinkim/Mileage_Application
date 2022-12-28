package com.habin.marketboro_mileage_task.mileage_event.repository;

import com.habin.marketboro_mileage_task.mileage_event.entity.MileageEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageEventRepository extends JpaRepository<MileageEvent, String>, QMileageEventRepository {

}
