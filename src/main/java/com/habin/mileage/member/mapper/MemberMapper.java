package com.habin.mileage.member.mapper;

import com.habin.mileage.common.mapper.EntityMapper;
import com.habin.mileage.member.dto.SignUpRequestDto;
import com.habin.mileage.member.entity.Member;
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
