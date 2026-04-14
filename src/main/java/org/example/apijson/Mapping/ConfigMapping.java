package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.ConfigDTO;
import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Entity.ConfigEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class ConfigMapping {

    public ConfigDTO toDTO(ConfigEntity entity) {
        if (entity == null) return null;

        ConfigDTO dto = new ConfigDTO();

        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setModifiedAt(entity.getModifiedAt());
        dto.setDefaultValue(entity.getDefaultValue());

        if (entity.getAttribute() != null) {
            dto.setAttributeId(entity.getAttribute().getId());
        }

        if (entity.getParentConfig() != null) {
            dto.setParent(entity.getParentConfig().getId());
        }

        return dto;
    }

    public ConfigEntity toEntity(ConfigDTO dto, AttributeEntity attribute, ConfigEntity parent) {
        if (dto == null) return null;
        ConfigEntity entity = new ConfigEntity();

        entity.setId(dto.getId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setModifiedAt(dto.getModifiedAt());
        entity.setDefaultValue(dto.getDefaultValue());

        entity.setAttribute(attribute);
        entity.setParentConfig(parent);

        return entity;
    }
}

