package org.example.apijson.Servicio;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.DTO.AttributeDTO;
import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.example.apijson.Entity.AttributeTypeValueEntity;
import org.example.apijson.Mapping.AttributeMapping;
import org.example.apijson.Repository.AttributeRepository;
import org.example.apijson.Repository.AttributeTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AttributeService implements IAttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeTypeRepository attributeTypeRepository;
    private final AttributeMapping attributeMapping;

    public List<AttributeDTO> buscartodosvivos() {
        return  attributeRepository.findAllByDeletedFalse().stream()
                .map(attributeMapping::toDTO)
                .toList();
    }

    @Override
    public List<AttributeDTO> buscartodosmuertos(){
        return  attributeRepository.findAllByDeletedTrue().stream()
                .map(attributeMapping::toDTO)
                .toList();
    }

    public AttributeDTO buscarporid(Long id) {


        return attributeRepository.findAllByDeletedFalse(id)
                .stream()
                .filter(a -> a.getId().equals(id))
                .map(attributeMapping::toDTO)
                .findFirst().orElseThrow();

    }

    @Override
    public void eliminarAttribute(Long id) {

            AttributeEntity attributeEntity = attributeRepository.findById(id)
                    .orElseThrow(RuntimeException::new);

            attributeEntity.setDeleted(true);
            attributeRepository.save(attributeEntity);

    }

    @Override
    public AttributeDTO aniadirA(AttributeDTO attribute) {

        AttributeTypeEntity atributetype = attributeTypeRepository.findById(attribute.getAttributeTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de atributo no encontrado"));

        AttributeEntity attributeEntity = AttributeMapping.toEntity(attribute, atributetype);

        AttributeDTO guardada = attributeMapping.toDTO(attributeRepository.save(attributeEntity));

        log.info("Atributo creado con exito ID: {} | Typo correcto: {}", attributeEntity.getId(), atributetype.getType());

        return guardada;

    }

    @Override
    public AttributeDTO actualizarA(Long id, AttributeDTO dto){

        AttributeTypeEntity atributetype = attributeTypeRepository.findById(id).orElseThrow();

        AttributeEntity attributeEntity = AttributeMapping.toEntity(dto, atributetype);
        AttributeDTO guardada = attributeMapping.toDTO(attributeRepository.save(attributeEntity));

        return guardada;

    }







}
