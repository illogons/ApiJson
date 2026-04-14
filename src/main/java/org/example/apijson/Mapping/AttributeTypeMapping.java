package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeDTO;
import org.example.apijson.DTO.AttributeTypeDTO;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttributeTypeMapping {

    public static AttributeTypeEntity toEntity(AttributeTypeDTO dto) {
        if (dto == null) return null;

        AttributeTypeEntity entity = new AttributeTypeEntity();

        entity.setType(dto.getType());
        entity.setId(dto.getId());
        entity.setIsEnum(dto.getIsEnum());
        entity.setIsList(dto.getIsList());

        entity.setDeleted(false);

        return entity;
    }
    public AttributeTypeDTO toDTO(AttributeTypeEntity entity) {
        if (entity == null) return null;

        AttributeTypeDTO dto = new AttributeTypeDTO();

        dto.setId(entity.getId());
        dto.setIsEnum(entity.getIsEnum());
        dto.setIsList(entity.getIsList());
        dto.setType(entity.getType());

        return dto;
    }
}
