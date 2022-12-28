package com.habin.marketboro_mileage_task.repository.querydsl;

import com.habin.marketboro_mileage_task.cache.SerializablePage;
import com.habin.marketboro_mileage_task.cache.SerializablePageExecutionUtils;
import com.habin.marketboro_mileage_task.dto.MileageListResponseDto;
import com.habin.marketboro_mileage_task.dto.TotalMileageResponseDto;
import com.habin.marketboro_mileage_task.entity.MileageType;
import com.habin.marketboro_mileage_task.module.jpa.PredicateTemplate;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.habin.marketboro_mileage_task.entity.QMember.member;
import static com.habin.marketboro_mileage_task.entity.QMileage.mileage;
import static com.querydsl.core.types.Projections.fields;

public class QMileageRepositoryImpl implements QMileageRepository {

    private final JPAQueryFactory queryFactory;

    public QMileageRepositoryImpl(EntityManager em) {
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
    @Cacheable(key = "#p0.concat('_').concat(#p1).concat('_').concat(#pageRequest.pageNumber).concat('_').concat(#pageRequest.pageSize)", value = "mileageList", cacheManager = "cacheManager")
    public SerializablePage<MileageListResponseDto> listWithPaging(String memberNo, MileageType mileageType, PageRequest pageRequest) {
        Predicate predicate = PredicateTemplate.builder()
                .eqString(member.memberNo, memberNo)
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
                .orderBy(mileage.insDtm.desc())
                .where(predicate)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(mileage.count())
                .from(mileage)
                .join(mileage.member, member)
                .orderBy(mileage.insDtm.desc())
                .where(predicate);

        return SerializablePageExecutionUtils.getPage(fetch, pageRequest, countQuery::fetchOne);
    }

    @Override
    public ConcurrentLinkedQueue<MileageListResponseDto> queue(String memberNo, MileageType mileageType) {
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
                .orderBy(mileage.insDtm.asc())
                .where(member.memberNo.eq(memberNo), mileage.mileageType.eq(mileageType))
                .fetch();

        return new ConcurrentLinkedQueue<>(fetch);
    }

}
