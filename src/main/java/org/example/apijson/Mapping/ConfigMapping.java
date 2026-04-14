package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.ConfigResponseDTO;
import org.example.apijson.Entity.AttributeEntity;
import org.springframework.beans.BeanUtils;
import org.example.apijson.DTO.ConfigRequestDto;
import org.example.apijson.Entity.ConfigEntity;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class ConfigMapping {

    public ConfigEntity toEntity(ConfigRequestDto dto) {
        if (dto == null) return null;
        ConfigEntity entity = new ConfigEntity();

        BeanUtils.copyProperties(dto, entity);

        if(dto.getParent() != null){
            ConfigEntity parent= new ConfigEntity();
            parent.setId(dto.getParent());
            entity.setParentConfig(parent);
        }

        if(dto.getAttributeId() != null){
            AttributeEntity attribute = new AttributeEntity();
            attribute.setId(dto.getAttributeId());
            entity.setAttribute(attribute);
        }

        return entity;
    }


    public ConfigResponseDTO toDTO(ConfigEntity entity) {
        if (entity == null){
            return null;
        }

        ConfigResponseDTO dto = new ConfigResponseDTO();

        BeanUtils.copyProperties(entity, dto);

        if (entity.getAttribute() != null) {
            dto.setAttributeId(entity.getAttribute().getId());
        }

        if (entity.getParentConfig() != null) {
            dto.setParent(entity.getParentConfig().getId());
        }

        return dto;
    }


}

