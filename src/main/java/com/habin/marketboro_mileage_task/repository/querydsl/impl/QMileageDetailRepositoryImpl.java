package com.habin.marketboro_mileage_task.repository.querydsl.impl;

import com.habin.marketboro_mileage_task.repository.querydsl.QMileageDetailRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

public class QMileageDetailRepositoryImpl implements QMileageDetailRepository {

    private final JPAQueryFactory queryFactory;

    public QMileageDetailRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

}
