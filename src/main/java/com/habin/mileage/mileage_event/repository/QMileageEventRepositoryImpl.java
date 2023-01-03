package com.habin.mileage.mileage_event.repository;

import com.habin.mileage.common.cache.SerializablePage;
import com.habin.mileage.common.cache.SerializablePageExecutionUtils;
import com.habin.mileage.common.dto.MileageListRequestDto;
import com.habin.mileage.common.jpa.PredicateTemplate;
import com.habin.mileage.mileage_event.dto.MileageEventListResponseDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.habin.mileage.member.entity.QMember.member;
import static com.habin.mileage.mileage_event.entity.QMileageEvent.mileageEvent;
import static com.querydsl.core.types.Projections.fields;

@RequiredArgsConstructor
public class QMileageEventRepositoryImpl implements QMileageEventRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    @Cacheable(key = "#dto.memberNo.concat('_')" +
            ".concat(#dto.mileageStatus).concat('_')" +
            ".concat(#dto.page).concat('_')" +
            ".concat(#dto.size)", value = "mileageList", cacheManager = "cacheManager")
    public SerializablePage<MileageEventListResponseDto> listWithPaging(MileageListRequestDto dto) {
        PageRequest pageRequest = dto.getPageRequest();

        Predicate predicate = PredicateTemplate.builder()
                .eqUUID(member.memberNo, dto.getMemberNo())
                .eqEnum(mileageEvent.mileageStatus, dto.getMileageStatus())
                .build();

        List<MileageEventListResponseDto> fetch = queryFactory.select(
                        fields(MileageEventListResponseDto.class,
                                mileageEvent.id.as("mileageEventId"),
                                mileageEvent.mileageStatus,
                                mileageEvent.amount,
                                mileageEvent.transactionDtm
                        )
                )
                .from(mileageEvent)
                .join(mileageEvent.member, member)
                .where(predicate)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .groupBy(member.memberNo)
                .orderBy(mileageEvent.transactionDtm.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(mileageEvent.id.count())
                .from(mileageEvent)
                .join(mileageEvent.member, member)
                .where(predicate)
                .groupBy(member.memberNo)
                .orderBy(mileageEvent.transactionDtm.desc())
                .where(predicate);

        return SerializablePageExecutionUtils.getPage(fetch, pageRequest, countQuery::fetchOne);
    }

}
