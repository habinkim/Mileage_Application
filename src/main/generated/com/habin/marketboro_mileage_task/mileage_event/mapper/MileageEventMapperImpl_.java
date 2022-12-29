package com.habin.marketboro_mileage_task.mileage_event.mapper;

import com.habin.marketboro_mileage_task.common.MileageStatus;
import com.habin.marketboro_mileage_task.common.mapper.EntityMapper;
import com.habin.marketboro_mileage_task.dto.MileageEventRequestDto;
import com.habin.marketboro_mileage_task.mileage_event.entity.MileageEvent;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-29T10:42:58+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class MileageEventMapperImpl_ implements MileageEventMapper {

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public MileageEvent mileageSaveDtoToEntity(MileageEventRequestDto mileageEventRequestDto, MileageStatus mileageStatus) {
        if ( mileageEventRequestDto == null && mileageStatus == null ) {
            return null;
        }

        MileageEvent.MileageEventBuilder mileageEvent = MileageEvent.builder();

        if ( mileageEventRequestDto != null ) {
            if ( mileageEventRequestDto.sum() != null ) {
                mileageEvent.sum( mileageEventRequestDto.sum() );
            }
            if ( mileageEventRequestDto.memberNo() != null ) {
                mileageEvent.member( entityMapper.memberNoToMember( mileageEventRequestDto.memberNo() ) );
            }
        }
        if ( mileageStatus != null ) {
            mileageEvent.mileageStatus( mileageStatus );
        }
        mileageEvent.transactionDtm( java.time.LocalDateTime.now() );

        return mileageEvent.build();
    }
}
