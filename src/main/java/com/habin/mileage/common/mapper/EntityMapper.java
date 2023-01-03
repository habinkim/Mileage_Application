package com.habin.mileage.common.mapper;

import com.habin.mileage.member.entity.Member;
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
