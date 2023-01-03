package com.habin.mileage.mileage_event.mapper;

import com.habin.mileage.mileage_event.dto.MileageEventCreateRequestDto;
import com.habin.mileage.mileage_event.entity.MileageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import static java.time.Period.ofYears;

@Primary
public abstract class MileageEventDecorator implements MileageEventMapper {

    @Autowired
    @Qualifier("delegate")
    private MileageEventMapper delegate;

    @Override
    public MileageEvent dtoToEntity(MileageEventCreateRequestDto dto) {
        MileageEvent mileageEvent = delegate.dtoToEntity(dto);
        return mileageEvent.toBuilder()
                .remainMileageExpireDtm(mileageEvent.getTransactionDtm().plus(ofYears(1)))
                .build();
    }
}
