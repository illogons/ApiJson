package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeDTO;
import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttributeMapping {

    public static AttributeEntity toEntity(AttributeDTO dto, AttributeTypeEntity type) {
        if(dto == null) {
            return null;
        }
        AttributeEntity entity = new AttributeEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAtributeType(type);
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setModifiedAt(dto.getModifiedAt());

        return entity;

    }

    public AttributeDTO toDTO(AttributeEntity entity) {
        if(entity == null) {
            return null;
        }

        AttributeDTO dto = new AttributeDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setModifiedAt(entity.getModifiedAt());
        return dto;
    }
}
