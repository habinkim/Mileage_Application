package com.habin.marketboro_mileage_task.mileage_detail.repository;

import com.habin.marketboro_mileage_task.mileage_event.entity.MileageEvent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.habin.marketboro_mileage_task.mileage_detail.entity.QMileageDetail.mileageDetail;
import static com.habin.marketboro_mileage_task.mileage_event.entity.QMileageEvent.mileageEvent;

public class QMileageDetailRepositoryImpl implements QMileageDetailRepository {

    private final JPAQueryFactory queryFactory;

    public QMileageDetailRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MileageEvent> availableMileageEvent(String memberNo) {

        queryFactory.select()
                .from(mileageDetail)
                .join(mileageDetail.member)
                .join(mileageDetail.mileageEvent)
                .where(mileageDetail.member.memberNo.eq(memberNo))
                .orderBy(mileageEvent.transactionDtm.asc())
                .groupBy(mileageDetail.saveMileageDetailId)
                .fetch();

        return null;
    }
}
