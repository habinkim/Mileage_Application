package com.habin.mileage.member.repository;

import com.habin.mileage.common.cache.SerializablePage;
import com.habin.mileage.common.dto.PageRequestDto;
import com.habin.mileage.member.dto.MileageAmountResponseDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

import static com.habin.mileage.common.cache.SerializablePageExecutionUtils.getPage;
import static com.habin.mileage.member.entity.QMember.member;
import static com.querydsl.core.types.Projections.fields;

@RequiredArgsConstructor
public class QMemberRepositoryImpl implements QMemberRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    @Cacheable(key = "#memberNo", value = "mileageAmount", cacheManager = "cacheManager")
    public MileageAmountResponseDto getMileageAmount(String memberNo) {
        return queryFactory
                .select(fields(MileageAmountResponseDto.class,
                        member.memberNo,
                        member.memberNm,
                        member.totalAmount
                ))
                .from(member)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .where(member.memberNo.eq(UUID.fromString(memberNo)))
                .fetchOne();
    }

    @Override
    @Cacheable(key = "page.concat('_').concat(size)", value = "mileageAmountList", cacheManager = "cacheManager")
    public SerializablePage<MileageAmountResponseDto> getMileageAmountList(PageRequestDto pageRequestDto) {
        PageRequest pageRequest = pageRequestDto.getPageRequest();

        List<MileageAmountResponseDto> fetch = queryFactory
                .select(fields(MileageAmountResponseDto.class,
                        member.memberNo,
                        member.memberNm,
                        member.totalAmount
                ))
                .from(member)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(member.memberNo.count())
                .from(member);

        return getPage(fetch, pageRequest, countQuery::fetchOne);
    }

    @Override
    public void updateTotalAmount(Integer currentTotalAmount) {
        queryFactory.update(member)
                .set(member.totalAmount, currentTotalAmount)
                .execute();
    }

}
