package com.habin.marketboro_mileage_task.common.mapper;

import com.habin.marketboro_mileage_task.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-29T10:42:58+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class EntityMapperImpl implements EntityMapper {

    @Autowired
    private ReferenceMapper referenceMapper;

    @Override
    public Member memberNoToMember(String memberNo) {
        if ( memberNo == null ) {
            return null;
        }

        Member member = referenceMapper.stringToEntity( memberNo, Member.class );

        return member;
    }
}
