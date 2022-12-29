package com.habin.marketboro_mileage_task.member.service;

import com.habin.marketboro_mileage_task.common.ApiResponse;
import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import com.habin.marketboro_mileage_task.member.dto.MileageAmountResponseDto;
import com.habin.marketboro_mileage_task.member.dto.SignUpRequestDto;
import com.habin.marketboro_mileage_task.member.entity.Member;
import com.habin.marketboro_mileage_task.member.mapper.MemberMapper;
import com.habin.marketboro_mileage_task.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ResponseEntity<ApiResponse<SerializablePage<MileageAmountResponseDto>>> getMileageAmountList(Integer page, Integer size) {
        SerializablePage<MileageAmountResponseDto> list = memberRepository.getMileageAmountList(page, size);
        return ApiResponse.success(list);
    }
}
