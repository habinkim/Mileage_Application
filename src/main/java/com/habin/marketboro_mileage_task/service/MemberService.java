package com.habin.marketboro_mileage_task.service;

import com.habin.marketboro_mileage_task.common.ApiResponse;
import com.habin.marketboro_mileage_task.dto.SignUpRequestDto;
import com.habin.marketboro_mileage_task.entity.Member;
import com.habin.marketboro_mileage_task.module.mapper.MemberMapper;
import com.habin.marketboro_mileage_task.repository.MemberRepository;
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
        member = memberRepository.save(member);

        return ApiResponse.success(member);
    }

}
