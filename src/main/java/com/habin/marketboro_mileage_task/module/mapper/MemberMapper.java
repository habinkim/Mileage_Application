package com.habin.marketboro_mileage_task.module.mapper;

import com.habin.marketboro_mileage_task.dto.SignUpRequestDto;
import com.habin.marketboro_mileage_task.entity.Member;
import com.habin.marketboro_mileage_task.module.mapper.base.EntityMapper;
import org.mapstruct.*;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MemberMapper {

    @Mapping(target = "mileage", ignore = true)
	@Mapping(target = "memberNo", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "totalMileageAmount", constant = "0")
	Member toEntity(SignUpRequestDto signUpRequestDto);

}
