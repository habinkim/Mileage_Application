package com.habin.marketboro_mileage_task.mileage_event.mapper;

import com.habin.marketboro_mileage_task.common.mapper.EntityMapper;
import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventRequestDto;
import com.habin.marketboro_mileage_task.mileage_event.entity.MileageEvent;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-29T14:04:22+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class MileageEventMapperImpl_ implements MileageEventMapper {

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public MileageEvent mileageSaveDtoToEntity(MileageEventRequestDto mileageEventRequestDto) {
        if ( mileageEventRequestDto == null ) {
            return null;
        }

        MileageEvent.MileageEventBuilder mileageEvent = MileageEvent.builder();

        if ( mileageEventRequestDto.amount() != null ) {
            mileageEvent.amount( mileageEventRequestDto.amount() );
        }
        if ( mileageEventRequestDto.mileageStatus() != null ) {
            mileageEvent.mileageStatus( mileageEventRequestDto.mileageStatus() );
        }
        if ( mileageEventRequestDto.memberNo() != null ) {
            mileageEvent.member( entityMapper.memberNoToMember( mileageEventRequestDto.memberNo() ) );
        }

        mileageEvent.transactionDtm( java.time.LocalDateTime.now() );

        return mileageEvent.build();
    }
}
