package com.habin.marketboro_mileage_task.module.mapper;

import com.habin.marketboro_mileage_task.dto.SignUpRequestDto;
import com.habin.marketboro_mileage_task.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-28T17:04:15+0900",
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
        member.totalMileageAmount( 0 );

        return member.build();
    }
}
