package com.habin.marketboro_mileage_task.member.mapper;

import com.habin.marketboro_mileage_task.common.mapper.EntityMapper;
import com.habin.marketboro_mileage_task.member.dto.SignUpRequestDto;
import com.habin.marketboro_mileage_task.member.entity.Member;
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

	@Mapping(target = "registerDtm", ignore = true)
	@Mapping(target = "memberNo", ignore = true)
	@Mapping(target = "totalAmount", constant = "0")
	Member toEntity(SignUpRequestDto signUpRequestDto);

}
