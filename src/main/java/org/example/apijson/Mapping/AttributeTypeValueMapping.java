package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeTypeValueRequestDTO;
import org.example.apijson.DTO.AttributeTypeValueResponseDTO;
import org.example.apijson.Model.AttributeTypeModel;
import org.example.apijson.Model.AttributeTypeValueModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttributeTypeValueMapping {

    public AttributeTypeValueModel toEntity(AttributeTypeValueRequestDTO dto) {
        if (dto == null) return null;

        AttributeTypeValueModel entity = new AttributeTypeValueModel();

        BeanUtils.copyProperties(dto, entity);

        if(dto.getAttributeTypeId() != null) {
            AttributeTypeModel type = new AttributeTypeModel();
            type.setId(dto.getAttributeTypeId());
            entity.setAttributeType(type);
        }

        return entity;
    }

    public AttributeTypeValueResponseDTO toDTO(AttributeTypeValueModel entity) {
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
