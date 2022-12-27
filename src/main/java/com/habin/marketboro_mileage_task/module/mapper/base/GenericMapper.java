package com.habin.marketboro_mileage_task.module.mapper.base;

public interface GenericMapper<E, D> {

	D toDto(E entity);

	E toEntity(D dto);

}
