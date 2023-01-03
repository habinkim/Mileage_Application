package com.habin.mileage.mileage_detail.repository;

import com.habin.mileage.common.cache.SerializablePage;
import com.habin.mileage.common.cache.SerializablePageExecutionUtils;
import com.habin.mileage.common.dto.MileageListRequestDto;
import com.habin.mileage.common.jpa.PredicateTemplate;
import com.habin.mileage.mileage_detail.dto.MileageDetailListResponseDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

import static com.habin.mileage.member.entity.QMember.member;
import static com.habin.mileage.mileage_detail.entity.QMileageDetail.mileageDetail;
import static com.habin.mileage.mileage_event.entity.QMileageEvent.mileageEvent;
import static com.querydsl.core.types.Projections.fields;

@RequiredArgsConstructor
public class QMileageDetailRepositoryImpl implements QMileageDetailRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public SerializablePage<MileageDetailListResponseDto> getMileageDetailList(MileageListRequestDto mileageListRequestDto) {

        Predicate predicate = PredicateTemplate.builder()
                .eqEnum(mileageDetail.mileageStatus, mileageListRequestDto.getMileageStatus())
                .build();

        PageRequest pageRequest = mileageListRequestDto.getPageRequest();

        List<MileageDetailListResponseDto> fetch = queryFactory.select(fields(MileageDetailListResponseDto.class,
                        mileageDetail.id.as("mileageDetailId"),
                        mileageEvent.id.as("mileageEventId"),
                        mileageDetail.mileageStatus,
                        mileageDetail.cancelMileageDetailId,
                        mileageDetail.saveMileageDetailId,
                        member.memberNo,
                        member.memberNm,
                        mileageDetail.mileageStatus.as("mileageStatus"),
                        mileageDetail.amount.as("amount"),
                        mileageDetail.transactionDtm.as("transactionDtm"),
                        mileageDetail.remainMileageExpireDtm.as("remainMileageExpireDtm")
                ))
                .from(mileageDetail)
                .join(mileageDetail.mileageEvent)
                .join(mileageDetail.member)
                .where(predicate, member.memberNo.eq(UUID.fromString(mileageListRequestDto.getMemberNo())))
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .orderBy(mileageDetail.transactionDtm.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(mileageDetail.id.count())
                .from(mileageDetail)
                .join(mileageDetail.mileageEvent)
                .join(mileageDetail.member)
                .where(predicate, member.memberNo.eq(UUID.fromString(mileageListRequestDto.getMemberNo())))
                .orderBy(mileageDetail.transactionDtm.desc());

        return SerializablePageExecutionUtils.getPage(fetch, pageRequest, countQuery::fetchOne);
    }
}
