package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeTypeReponseDTO;
import org.example.apijson.DTO.AttributeTypeRequestDTO;
import org.springframework.beans.BeanUtils;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
@RequiredArgsConstructor
public class AttributeTypeMapping {

    public static AttributeTypeEntity toEntity(AttributeTypeRequestDTO dto) throws InvocationTargetException, IllegalAccessException {
        if (dto == null) return null;

        AttributeTypeEntity entity = new AttributeTypeEntity();

        BeanUtils.copyProperties(dto, entity, "id");

        return entity;
    }
    public AttributeTypeReponseDTO toDTO(AttributeTypeEntity entity) {
        if (entity == null) return null;

        AttributeTypeReponseDTO dto = new AttributeTypeReponseDTO();

        BeanUtils.copyProperties(entity, dto);

        return dto;
    }
}
