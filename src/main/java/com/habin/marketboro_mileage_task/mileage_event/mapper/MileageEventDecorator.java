package com.habin.marketboro_mileage_task.mileage_event.mapper;

import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventRequestDto;
import com.habin.marketboro_mileage_task.mileage_event.entity.MileageEvent;
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
    public MileageEvent mileageSaveDtoToEntity(MileageEventRequestDto mileageEventRequestDto) {
        MileageEvent mileageEvent = delegate.mileageSaveDtoToEntity(mileageEventRequestDto);
        return mileageEvent.toBuilder()
                .remainMileageExpireDtm(mileageEvent.getTransactionDtm().plus(ofYears(1)))
                .build();
    }
}
