package org.example.apijson.Mapping;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeRequestDTO;
import org.example.apijson.DTO.AttributeResponseDTO;
import org.example.apijson.Model.AttributeModel;
import org.example.apijson.Model.AttributeTypeModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttributeMapping {

    public AttributeModel toEntity(AttributeRequestDTO dto)  {
        if(dto == null) {
            return null;
        }
        AttributeModel entity = new AttributeModel();

        BeanUtils.copyProperties(dto, entity, "id", "createdAt", "modifiedAt", "deleted");

        if(dto.getAttributeTypeId() != null) {
            AttributeTypeModel type= new AttributeTypeModel();
            type.setId(dto.getAttributeTypeId());
            entity.setAtributeType(type);
        }


        return entity;

    }

    public AttributeResponseDTO toDTO(AttributeModel entity)  {
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
