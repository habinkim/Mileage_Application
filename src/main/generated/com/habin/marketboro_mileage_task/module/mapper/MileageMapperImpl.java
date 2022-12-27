package com.habin.marketboro_mileage_task.module.mapper;

import com.habin.marketboro_mileage_task.dto.MileageSaveRequestDto;
import com.habin.marketboro_mileage_task.entity.Mileage;
import com.habin.marketboro_mileage_task.entity.enums.MileageType;
import com.habin.marketboro_mileage_task.module.mapper.base.EntityMapper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-27T13:13:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MileageMapperImpl implements MileageMapper {

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public Mileage mileageSaveDtoToEntity(MileageSaveRequestDto mileageSaveRequestDto) {
        if ( mileageSaveRequestDto == null ) {
            return null;
        }

        Mileage.MileageBuilder<?, ?> mileage = Mileage.builder();

        if ( mileageSaveRequestDto.getMemberNo() != null ) {
            mileage.member( entityMapper.memberNoToMember( mileageSaveRequestDto.getMemberNo() ) );
        }
        if ( mileageSaveRequestDto.getSum() != null ) {
            mileage.sum( mileageSaveRequestDto.getSum() );
        }

        mileage.mileageType( MileageType.SAVE );

        return mileage.build();
    }
}
