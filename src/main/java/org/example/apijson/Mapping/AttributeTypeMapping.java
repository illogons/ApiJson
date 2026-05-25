package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeTypeReponseDTO;
import org.example.apijson.DTO.AttributeTypeRequestDTO;
import org.springframework.beans.BeanUtils;
import org.example.apijson.Model.AttributeTypeModel;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
@RequiredArgsConstructor
public class AttributeTypeMapping {

    public static AttributeTypeModel toEntity(AttributeTypeRequestDTO dto) throws InvocationTargetException, IllegalAccessException {
        if (dto == null) return null;

        AttributeTypeModel entity = new AttributeTypeModel();

        BeanUtils.copyProperties(dto, entity, "id");

        return entity;
    }
    public AttributeTypeReponseDTO toDTO(AttributeTypeModel entity) {
        if (entity == null) return null;

        AttributeTypeReponseDTO dto = new AttributeTypeReponseDTO();

        BeanUtils.copyProperties(entity, dto);

        return dto;
    }
}
