package com.habin.marketboro_mileage_task.repository;

import com.habin.marketboro_mileage_task.entity.Mileage;
import com.habin.marketboro_mileage_task.repository.querydsl.QMileageRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageRepository extends JpaRepository<Mileage, Long>, QMileageRepository {

}
