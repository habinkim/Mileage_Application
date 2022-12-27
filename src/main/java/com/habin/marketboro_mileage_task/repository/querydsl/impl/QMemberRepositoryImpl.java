package com.habin.marketboro_mileage_task.repository.querydsl.impl;

import com.habin.marketboro_mileage_task.repository.querydsl.QMemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

public class QMemberRepositoryImpl implements QMemberRepository {

    private final JPAQueryFactory queryFactory;

    public QMemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

}
