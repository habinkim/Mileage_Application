package com.habin.marketboro_mileage_task.accumulated_mileage.repository;

import com.habin.marketboro_mileage_task.accumulated_mileage.entity.AccumulatedMileage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccumulatedMileageMileageRepository extends JpaRepository<AccumulatedMileage, Long>, QAccumulatedMileageRepository {

}
