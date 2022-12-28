package com.habin.marketboro_mileage_task.module.mapper.base;

import com.habin.marketboro_mileage_task.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-28T17:04:15+0900",
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
