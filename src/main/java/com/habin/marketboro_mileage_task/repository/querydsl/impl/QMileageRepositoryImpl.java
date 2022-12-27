package com.habin.marketboro_mileage_task.repository.querydsl.impl;

import com.habin.marketboro_mileage_task.dto.MileageListResponseDto;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.enums.MileageType;
import com.habin.marketboro_mileage_task.module.jpa.PredicateTemplate;
import com.habin.marketboro_mileage_task.repository.querydsl.QMileageRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.habin.marketboro_mileage_task.entity.QMember.member;
import static com.habin.marketboro_mileage_task.entity.QMileage.mileage;
import static com.querydsl.core.types.Projections.fields;
import static org.springframework.data.support.PageableExecutionUtils.getPage;

public class QMileageRepositoryImpl implements QMileageRepository {

    private final JPAQueryFactory queryFactory;

    public QMileageRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<TotalMileageResponseDto> total(String memberNo) {
        Predicate predicate = PredicateTemplate.builder()
                .containsString(member.memberNo, memberNo)
                .build();

        return queryFactory.select(
                        fields(TotalMileageResponseDto.class,
                                member.memberNo,
                                member.memberNm,
                                mileage.sum.sum().as("totalMileageAmount")
                        )
                )
                .from(mileage)
                .join(mileage.member, member)
                .where(predicate, mileage.mileageType.eq(MileageType.SAVE))
                .groupBy(member.memberNo, mileage.mileageType)
                .fetch();
    }

    @Override
    public Page<MileageListResponseDto> list(String memberNo, MileageType mileageType, PageRequest pageRequest) {
        Predicate predicate = PredicateTemplate.builder()
                .containsString(member.memberNo, memberNo)
                .eqEnum(mileage.mileageType, mileageType)
                .build();

        List<MileageListResponseDto> fetch = queryFactory.select(
                        fields(MileageListResponseDto.class,
                                mileage.mileageIdx,
                                mileage.mileageType,
                                mileage.sum,
                                mileage.insDtm
                        )
                )
                .from(mileage)
                .join(mileage.member, member)
                .where(predicate)
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(mileage.count())
                .from(mileage)
                .join(mileage.member, member)
                .where(predicate);

        return getPage(fetch, pageRequest, countQuery::fetchOne);
    }
}
