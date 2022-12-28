package com.habin.marketboro_mileage_task.mileage_detail.repository;

import com.habin.marketboro_mileage_task.mileage_detail.entity.MileageDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageDetailRepository extends JpaRepository<MileageDetail, String>, QMileageDetailRepository {

}
