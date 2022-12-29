package com.habin.marketboro_mileage_task.mileage_event.mapper;

import com.habin.marketboro_mileage_task.common.mapper.EntityMapper;
import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventCreateRequestDto;
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
	@Mapping(target = "remainMileageExpireDtm", ignore = true)
    @Mapping(target = "transactionDtm", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "amount", source = "amount")
    @Mapping(target = "mileageStatus", source = "mileageStatus")
    @Mapping(target = "member", source = "memberNo")
    MileageEvent dtoToEntity(MileageEventCreateRequestDto dto);

}
