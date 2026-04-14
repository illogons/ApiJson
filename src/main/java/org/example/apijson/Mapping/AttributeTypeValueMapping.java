package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeTypeValueRequestDTO;
import org.example.apijson.DTO.AttributeTypeValueResponseDTO;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.example.apijson.Entity.AttributeTypeValueEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttributeTypeValueMapping {

    public  AttributeTypeValueEntity toEntity(AttributeTypeValueRequestDTO dto) {
        if (dto == null) return null;

        AttributeTypeValueEntity entity = new AttributeTypeValueEntity();

        BeanUtils.copyProperties(dto, entity);

        if(dto.getAttributeTypeId() != null) {
            AttributeTypeEntity type = new AttributeTypeEntity();
            type.setId(dto.getAttributeTypeId());
            entity.setAttributeType(type);
        }

        return entity;
    }

    public AttributeTypeValueResponseDTO toDTO(AttributeTypeValueEntity entity) {
        if (entity == null) {
            return null;
        }

        AttributeTypeValueResponseDTO dto = new AttributeTypeValueResponseDTO();

        BeanUtils.copyProperties(entity, dto);

        if(entity.getAttributeType() != null) {
            dto.setAttributeTypeId(entity.getAttributeType().getId());
        }

        return dto;
    }


}
