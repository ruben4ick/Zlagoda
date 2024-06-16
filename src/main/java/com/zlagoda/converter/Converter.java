package com.zlagoda.converter;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public abstract class Converter<E, D> {

    protected final ModelMapper mapper;
    private final Class<E> entityType;
    private final Class<D> dtoType;


    public Converter(ModelMapper mapper, Class<E> entityType, Class<D> dtoType) {
        this.mapper = mapper;
        this.entityType = entityType;
        this.dtoType = dtoType;
    }

    public E convertToEntity(D dto) {
        E entity = mapper.map(dto, entityType);
        log.debug("[CONVERTER] {} converted to {}", dto, entity);
        return entity;
    }

    public D convertToDto(E entity) {
        D dto = mapper.map(entity, dtoType);
        log.debug("[CONVERTER] {} converted to {}", entity, dto);
        return dto;
    }

}