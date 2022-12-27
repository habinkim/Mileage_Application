package com.habin.marketboro_mileage_task.module.mapper.base;

import com.habin.marketboro_mileage_task.entity.Member;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
		uses = {ReferenceMapper.class},
		builder = @Builder(disableBuilder = true),
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EntityMapper {

	Member memberNoToMember(String memberNo);

}
