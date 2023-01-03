package com.habin.mileage.member.mapper;

import com.habin.mileage.member.dto.SignUpRequestDto;
import com.habin.mileage.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-03T14:39:49+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member toEntity(SignUpRequestDto signUpRequestDto) {
        if ( signUpRequestDto == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        if ( signUpRequestDto.memberNm() != null ) {
            member.memberNm( signUpRequestDto.memberNm() );
        }

        member.totalAmount( 0 );

        return member.build();
    }
}
