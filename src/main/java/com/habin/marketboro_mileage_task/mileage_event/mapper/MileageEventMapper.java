package com.habin.marketboro_mileage_task.mileage_event.mapper;

import com.habin.marketboro_mileage_task.common.MileageStatus;
import com.habin.marketboro_mileage_task.common.mapper.EntityMapper;
import com.habin.marketboro_mileage_task.dto.MileageEventRequestDto;
import com.habin.marketboro_mileage_task.mileage_event.entity.MileageEvent;
import org.mapstruct.*;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@DecoratedWith(MileageEventDecorator.class)
public interface MileageEventMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "mileageDetail", ignore = true)
	@Mapping(target = "remainMileageExpireDtm", ignore = true)
    @Mapping(target = "transactionDtm", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "sum", source = "mileageEventRequestDto.sum")
    @Mapping(target = "mileageStatus", source = "mileageStatus")
    @Mapping(target = "member", source = "mileageEventRequestDto.memberNo")
    MileageEvent mileageSaveDtoToEntity(MileageEventRequestDto mileageEventRequestDto, MileageStatus mileageStatus);

}
