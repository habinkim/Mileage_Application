package com.habin.mileage.mileage_event.mapper;

import com.habin.mileage.common.mapper.EntityMapper;
import com.habin.mileage.mileage_event.dto.MileageEventCreateRequestDto;
import com.habin.mileage.mileage_event.entity.MileageEvent;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-03T14:39:49+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class MileageEventMapperImpl_ implements MileageEventMapper {

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public MileageEvent dtoToEntity(MileageEventCreateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        MileageEvent.MileageEventBuilder mileageEvent = MileageEvent.builder();

        if ( dto.getAmount() != null ) {
            mileageEvent.amount( dto.getAmount() );
        }
        if ( dto.getMileageStatus() != null ) {
            mileageEvent.mileageStatus( dto.getMileageStatus() );
        }
        if ( dto.getMemberNo() != null ) {
            mileageEvent.member( entityMapper.memberNoToMember( dto.getMemberNo() ) );
        }

        mileageEvent.transactionDtm( java.time.LocalDateTime.now() );

        return mileageEvent.build();
    }
}
