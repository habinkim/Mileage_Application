package com.habin.marketboro_mileage_task.mileage_event.repository;

import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.common.cache.SerializablePageExecutionUtils;
import com.habin.marketboro_mileage_task.common.jpa.PredicateTemplate;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.enums.MileageStatus;
import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventListResponseDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.habin.marketboro_mileage_task.member.entity.QMember.member;
import static com.habin.marketboro_mileage_task.mileage_event.entity.QMileageEvent.mileageEvent;
import static com.querydsl.core.types.Projections.fields;

public class QMileageEventRepositoryImpl implements QMileageEventRepository {

    private final JPAQueryFactory queryFactory;

    public QMileageEventRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    @Cacheable(key = "#p0", value = "totalMileage", cacheManager = "cacheManager")
    public List<TotalMileageResponseDto> total(String memberNo) {
        Predicate predicate = PredicateTemplate.builder()
                .containsString(member.memberNo, memberNo)
                .build();

        return queryFactory.select(
                        fields(TotalMileageResponseDto.class,
                                member.memberNo,
                                member.memberNm,
                                mileageEvent.sum.sum().as("totalMileageAmount")
                        )
                )
                .from(mileageEvent)
                .join(mileageEvent.member, member)
                .where(predicate, mileageEvent.mileageStatus.eq(MileageStatus.SAVE))
                .groupBy(member.memberNo, mileageEvent.mileageStatus)
                .fetch();
    }

    @Override
    @Cacheable(key = "#p0.concat('_').concat(#p1).concat('_').concat(#pageRequest.pageNumber).concat('_').concat(#pageRequest.pageSize)", value = "mileageList", cacheManager = "cacheManager")
    public SerializablePage<MileageEventListResponseDto> listWithPaging(String memberNo, MileageStatus mileageStatus, PageRequest pageRequest) {
        Predicate predicate = PredicateTemplate.builder()
                .eqString(member.memberNo, memberNo)
                .eqEnum(mileageEvent.mileageStatus, mileageStatus)
                .build();

        List<MileageEventListResponseDto> fetch = queryFactory.select(
                        fields(MileageEventListResponseDto.class,
                                mileageEvent.id.as("mileageEventId"),
                                mileageEvent.mileageStatus,
                                mileageEvent.sum,
                                mileageEvent.transactionDtm
                        )
                )
                .from(mileageEvent)
                .join(mileageEvent.member, member)
                .orderBy(mileageEvent.transactionDtm.desc())
                .where(predicate)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(mileageEvent.count())
                .from(mileageEvent)
                .join(mileageEvent.member, member)
                .orderBy(mileageEvent.transactionDtm.desc())
                .where(predicate);

        return SerializablePageExecutionUtils.getPage(fetch, pageRequest, countQuery::fetchOne);
    }

    @Override
    public ConcurrentLinkedQueue<MileageEventListResponseDto> queue(String memberNo, MileageStatus mileageStatus) {
        List<MileageEventListResponseDto> fetch = queryFactory.select(
                        fields(MileageEventListResponseDto.class,
                                mileageEvent.id.as("mileageEventId"),
                                mileageEvent.mileageStatus,
                                mileageEvent.sum,
                                mileageEvent.transactionDtm
                        )
                )
                .from(mileageEvent)
                .join(mileageEvent.member, member)
                .orderBy(mileageEvent.transactionDtm.asc())
                .where(member.memberNo.eq(memberNo), mileageEvent.mileageStatus.eq(mileageStatus))
                .fetch();

        return new ConcurrentLinkedQueue<>(fetch);
    }

}
