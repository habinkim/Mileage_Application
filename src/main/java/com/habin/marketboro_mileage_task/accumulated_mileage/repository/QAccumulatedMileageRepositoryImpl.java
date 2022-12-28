package com.habin.marketboro_mileage_task.accumulated_mileage.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QAccumulatedMileageRepositoryImpl implements QAccumulatedMileageRepository {

    private final JPAQueryFactory queryFactory;

}
