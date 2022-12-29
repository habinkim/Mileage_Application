package com.habin.marketboro_mileage_task.member.repository;

import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.member.dto.MileageAmountResponseDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

import static com.habin.marketboro_mileage_task.common.cache.SerializablePageExecutionUtils.getPage;
import static com.habin.marketboro_mileage_task.member.entity.QMember.member;
import static com.querydsl.core.types.Projections.fields;

@RequiredArgsConstructor
public class QMemberRepositoryImpl implements QMemberRepository {

    private final JPAQueryFactory queryFactory;

    private final JPAQuery<MileageAmountResponseDto> selectBaseQuery = queryFactory
            .select(fields(MileageAmountResponseDto.class,
                    member.memberNo,
                    member.memberNm,
                    member.totalAmount
            ))
            .from(member);

    @Override
    @Cacheable(key = "#memberNo", value = "mileageAmount", cacheManager = "cacheManager")
    public MileageAmountResponseDto getMileageAmount(String memberNo) {
        return selectBaseQuery.where(member.memberNo.eq(UUID.fromString(memberNo))).fetchOne();
    }

    @Override
    @Cacheable(key = "page.concat('_').concat(size)", value = "mileageAmountList", cacheManager = "cacheManager")
    public SerializablePage<MileageAmountResponseDto> getMileageAmountList(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        List<MileageAmountResponseDto> fetch = selectBaseQuery
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(member.memberNo.count())
                .from(member);

        return getPage(fetch, pageRequest, countQuery::fetchOne);
    }

}
