package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.ConfigResponseDTO;
import org.example.apijson.Model.AttributeModel;
import org.springframework.beans.BeanUtils;
import org.example.apijson.DTO.ConfigRequestDto;
import org.example.apijson.Model.ConfigModel;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class ConfigMapping {

    public ConfigModel toEntity(ConfigRequestDto dto) {
        if (dto == null) return null;
        ConfigModel entity = new ConfigModel();

        BeanUtils.copyProperties(dto, entity);

        if(dto.getParent() != null){
            ConfigModel parent= new ConfigModel();
            parent.setId(dto.getParent());
            entity.setParentConfig(parent);
        }

        if(dto.getAttributeId() != null){
            AttributeModel attribute = new AttributeModel();
            attribute.setId(dto.getAttributeId());
            entity.setAttribute(attribute);
        }

        return entity;
    }


    public ConfigResponseDTO toDTO(ConfigModel entity) {
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

