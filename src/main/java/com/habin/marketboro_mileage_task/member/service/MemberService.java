package com.habin.marketboro_mileage_task.member.service;

import com.habin.marketboro_mileage_task.common.MileageStatus;
import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.common.dto.ApiResponse;
import com.habin.marketboro_mileage_task.common.dto.PageRequestDto;
import com.habin.marketboro_mileage_task.member.dto.MileageAmountResponseDto;
import com.habin.marketboro_mileage_task.member.dto.SignUpRequestDto;
import com.habin.marketboro_mileage_task.member.entity.Member;
import com.habin.marketboro_mileage_task.member.mapper.MemberMapper;
import com.habin.marketboro_mileage_task.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.habin.marketboro_mileage_task.common.expression.StaticLambdaExpression.getNSEE;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public ResponseEntity<ApiResponse<Member>> signUp(SignUpRequestDto signUpRequestDto) {
        Member member = memberMapper.toEntity(signUpRequestDto);
        memberRepository.save(member);

        return ApiResponse.success();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<MileageAmountResponseDto>> getMileageAmount(String memberNo) {
        MileageAmountResponseDto accumulatedMileage = memberRepository.getMileageAmount(memberNo);
        return ApiResponse.success(accumulatedMileage);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<SerializablePage<MileageAmountResponseDto>>> getMileageAmountList(PageRequestDto pageRequestDto) {
        SerializablePage<MileageAmountResponseDto> list = memberRepository.getMileageAmountList(pageRequestDto);
        return ApiResponse.success(list);
    }

    public void updateMileageAmount(String memberNo, MileageStatus mileageStatus, Integer amount) {
        Member member = memberRepository.findById(memberNo).orElseThrow(getNSEE.apply("사용자 정보"));
        Integer currentTotalAmount = calcUpdateAmount(mileageStatus, amount, member.getTotalAmount());
        memberRepository.updateTotalAmount(currentTotalAmount);
    }

    private Integer calcUpdateAmount(MileageStatus mileageStatus, Integer amount, Integer beforeTotalAmount) {
        return !mileageStatus.equals(MileageStatus.USE) ? amount : (amount * (-1)) + beforeTotalAmount;
    }

}
