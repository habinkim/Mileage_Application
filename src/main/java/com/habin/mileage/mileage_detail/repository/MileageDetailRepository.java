package com.habin.mileage.mileage_detail.repository;

import com.habin.mileage.mileage_detail.entity.MileageDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageDetailRepository extends JpaRepository<MileageDetail, String>, QMileageDetailRepository {

}
