package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeTypeValueDTO;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.example.apijson.Entity.AttributeTypeValueEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttributeTypeValueMapping {


    public AttributeTypeValueDTO toDTO(AttributeTypeValueEntity entity) {
        if (entity == null) {
            return null;
        }

        AttributeTypeValueDTO dto = new AttributeTypeValueDTO();

        dto.setId(entity.getId());
        dto.setDeleted(entity.getDeleted());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setModifiedAt(entity.getModifiedAt());
        dto.setValue(entity.getValue());

        if(entity.getAttributeType() != null) {
            dto.setAttributeTypeId(entity.getAttributeType().getId());
        }

        return dto;
    }

    public  AttributeTypeValueEntity toEntity(AttributeTypeValueDTO dto, AttributeTypeEntity atributetype) {
        if (dto == null) return null;

        AttributeTypeValueEntity entity = new AttributeTypeValueEntity();

        entity.setId(dto.getId());
        entity.setAttributeType(atributetype);
        entity.setValue(dto.getValue());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setModifiedAt(dto.getModifiedAt());


        return entity;
    }
}
