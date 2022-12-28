package com.habin.marketboro_mileage_task.module.mapper;

import com.habin.marketboro_mileage_task.dto.MileageRequestDto;
import com.habin.marketboro_mileage_task.entity.Mileage;
import com.habin.marketboro_mileage_task.entity.MileageType;
import com.habin.marketboro_mileage_task.module.mapper.base.EntityMapper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-28T17:04:15+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MileageMapperImpl implements MileageMapper {

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public Mileage mileageSaveDtoToEntity(MileageRequestDto mileageRequestDto, MileageType mileageType) {
        if ( mileageRequestDto == null && mileageType == null ) {
            return null;
        }

        Mileage.MileageBuilder<?, ?> mileage = Mileage.builder();

        if ( mileageRequestDto != null ) {
            if ( mileageRequestDto.sum() != null ) {
                mileage.sum( mileageRequestDto.sum() );
            }
            if ( mileageRequestDto.memberNo() != null ) {
                mileage.member( entityMapper.memberNoToMember( mileageRequestDto.memberNo() ) );
            }
        }
        if ( mileageType != null ) {
            mileage.mileageType( mileageType );
        }

        return mileage.build();
    }
}
