package com.habin.marketboro_mileage_task.repository;

import com.habin.marketboro_mileage_task.entity.MileageDetail;
import com.habin.marketboro_mileage_task.repository.querydsl.QMileageDetailRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageDetailRepository extends JpaRepository<MileageDetail, String>, QMileageDetailRepository {

}
