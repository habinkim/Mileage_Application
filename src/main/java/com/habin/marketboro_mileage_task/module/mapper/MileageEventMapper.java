package com.habin.marketboro_mileage_task.module.mapper;

import com.habin.marketboro_mileage_task.dto.MileageRequestDto;
import com.habin.marketboro_mileage_task.entity.MileageEvent;
import com.habin.marketboro_mileage_task.entity.enums.MileageStatus;
import com.habin.marketboro_mileage_task.module.mapper.base.EntityMapper;
import com.habin.marketboro_mileage_task.module.mapper.decorator.MileageEventDecorator;
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

	@Mapping(target = "mileageEventId", ignore = true)
	@Mapping(target = "remainMileageExpireDtm", ignore = true)
    @Mapping(target = "transactionDtm", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "sum", source = "mileageRequestDto.sum")
    @Mapping(target = "mileageStatus", source = "mileageStatus")
    @Mapping(target = "member", source = "mileageRequestDto.memberNo")
    MileageEvent mileageSaveDtoToEntity(MileageRequestDto mileageRequestDto, MileageStatus mileageStatus);

}
