package org.example.apijson.Servicio;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.DTO.AttributeDTO;
import org.example.apijson.DTO.AttributeTypeDTO;
import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.example.apijson.Entity.AttributeTypeValueEntity;
import org.example.apijson.Mapping.AttributeMapping;
import org.example.apijson.Mapping.AttributeTypeMapping;
import org.example.apijson.Repository.AttributeTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AttributeTypeService implements IAtributteTypeService {
    
    private final AttributeTypeRepository attributeTypeRepository;
    private final AttributeTypeMapping attributeTypeMapping;


    @Override
    public AttributeTypeDTO listarid(Long id) {

        return attributeTypeRepository.findIdByDeletedFalse(id)
                .stream()
                .filter(a -> a.getId().equals(id))
                .map(attributeTypeMapping::toDTO)
                .findFirst().orElseThrow();
    }

    @Override
    public List<AttributeTypeDTO> listartodosvivos() {
        return attributeTypeRepository.findAllByDeletedFalse()
                .stream()
                .map(attributeTypeMapping::toDTO)
                .toList();

    }

    @Override
    public List<AttributeTypeDTO> buscartodosmuertos() {
        return  attributeTypeRepository.findAllByDeletedTrue()
                .stream()
                .map(attributeTypeMapping::toDTO)
                .toList();
    }

    @Override
    public AttributeTypeDTO aniadir(AttributeTypeDTO dto) {
        if(attributeTypeRepository.existsById(dto.getId())){
        log.error("ya existe este id");
        throw new RuntimeException("ya existe este id");
        }

        AttributeTypeEntity attributeTypeEntity = AttributeTypeMapping.toEntity(dto);
        AttributeTypeDTO guardado = attributeTypeMapping.toDTO(attributeTypeRepository.save(attributeTypeEntity));
        log.info("guardado: {}", guardado);

        return guardado;


    }

    @Override
    public void eliminar(Long id) {

        AttributeTypeEntity attributeTypeEntity = attributeTypeRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        attributeTypeEntity.setDeleted(true);
        attributeTypeRepository.save(attributeTypeEntity);
    }

    @Override
    public AttributeTypeDTO actualizar(Long id, AttributeTypeDTO dto) {

        if (dto.getId() != null && !id.equals(dto.getId())) {
            log.error("Error: ID URL ({}) no coincide con ID JSON ({})", id, dto.getId());
            throw new RuntimeException("El ID del cuerpo no coincide con el ID de la URL");
        }

        if(attributeTypeRepository.existsById(id)) {

            AttributeTypeEntity attributeTypeEntity = attributeTypeRepository.findById(id)
                    .orElseThrow();

            attributeTypeEntity.setType(dto.getType());
            attributeTypeEntity.setIsList(dto.getIsList());
            attributeTypeEntity.setIsEnum(dto.getIsEnum());

            attributeTypeRepository.save(attributeTypeEntity);

            return attributeTypeMapping.toDTO(attributeTypeEntity);
        }

        return dto;
    }


}
