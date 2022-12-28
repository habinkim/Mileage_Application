package com.habin.marketboro_mileage_task.module.mapper;

import com.habin.marketboro_mileage_task.dto.MileageRequestDto;
import com.habin.marketboro_mileage_task.entity.MileageEvent;
import com.habin.marketboro_mileage_task.entity.enums.MileageStatus;
import com.habin.marketboro_mileage_task.module.mapper.base.EntityMapper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-28T22:36:47+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class MileageEventMapperImpl_ implements MileageEventMapper {

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public MileageEvent mileageSaveDtoToEntity(MileageRequestDto mileageRequestDto, MileageStatus mileageStatus) {
        if ( mileageRequestDto == null && mileageStatus == null ) {
            return null;
        }

        MileageEvent.MileageEventBuilder<?, ?> mileageEvent = MileageEvent.builder();

        if ( mileageRequestDto != null ) {
            if ( mileageRequestDto.sum() != null ) {
                mileageEvent.sum( mileageRequestDto.sum() );
            }
            if ( mileageRequestDto.memberNo() != null ) {
                mileageEvent.member( entityMapper.memberNoToMember( mileageRequestDto.memberNo() ) );
            }
        }
        if ( mileageStatus != null ) {
            mileageEvent.mileageStatus( mileageStatus );
        }
        mileageEvent.transactionDtm( java.time.LocalDateTime.now() );

        return mileageEvent.build();
    }
}
