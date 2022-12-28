package com.habin.marketboro_mileage_task.member.mapper;

import com.habin.marketboro_mileage_task.member.dto.SignUpRequestDto;
import com.habin.marketboro_mileage_task.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-29T00:03:31+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member toEntity(SignUpRequestDto signUpRequestDto) {
        if ( signUpRequestDto == null ) {
            return null;
        }

        Member.MemberBuilder<?, ?> member = Member.builder();

        if ( signUpRequestDto.memberNm() != null ) {
            member.memberNm( signUpRequestDto.memberNm() );
        }

        member.memberNo( java.util.UUID.randomUUID().toString() );

        return member.build();
    }
}
