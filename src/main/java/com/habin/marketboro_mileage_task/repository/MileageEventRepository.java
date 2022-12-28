package com.habin.marketboro_mileage_task.repository;

import com.habin.marketboro_mileage_task.entity.MileageEvent;
import com.habin.marketboro_mileage_task.repository.querydsl.QMileageEventRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageEventRepository extends JpaRepository<MileageEvent, String>, QMileageEventRepository {

}
