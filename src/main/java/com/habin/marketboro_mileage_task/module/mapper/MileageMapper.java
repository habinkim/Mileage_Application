package com.habin.marketboro_mileage_task.module.mapper;

import com.habin.marketboro_mileage_task.dto.MileageSaveRequestDto;
import com.habin.marketboro_mileage_task.entity.Mileage;
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
public interface MileageMapper {

    @Mapping(target = "mileageIdx", ignore = true)
    @Mapping(target = "mileageType", constant = "SAVE")
    @Mapping(target = "member", source = "memberNo")
    Mileage mileageSaveDtoToEntity(MileageSaveRequestDto mileageSaveRequestDto);

}
