package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeRequestDTO;
import org.example.apijson.DTO.AttributeResponseDTO;
import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
@RequiredArgsConstructor
public class AttributeMapping {

    public AttributeEntity toEntity(AttributeRequestDTO dto)  {
        if(dto == null) {
            return null;
        }
        AttributeEntity entity = new AttributeEntity();

        BeanUtils.copyProperties(dto, entity, "id", "createdAt", "modifiedAt");

        if(dto.getAttributeTypeId() != null) {
            AttributeTypeEntity type= new AttributeTypeEntity();
            type.setId(dto.getAttributeTypeId());
            entity.setAtributeType(type);
        }


        return entity;

    }

    public AttributeResponseDTO toDTO(AttributeEntity entity)  {
        if(entity == null) {
            return null;
        }
        AttributeResponseDTO dto = new AttributeResponseDTO();
        BeanUtils.copyProperties(entity, dto);


        if(entity.getAtributeType() != null) {
            dto.setAttributeTypeId(entity.getAtributeType().getId());
        }

        return dto;
    }
}
