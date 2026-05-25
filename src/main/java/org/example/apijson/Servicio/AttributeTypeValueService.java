package org.example.apijson.Servicio;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.DTO.AttributeTypeValueRequestDTO;
import org.example.apijson.DTO.AttributeTypeValueResponseDTO;
import org.example.apijson.Model.AttributeTypeValueModel;
import org.example.apijson.Mapping.AttributeTypeValueMapping;
import org.example.apijson.Repository.AttributeTypeValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AttributeTypeValueService implements IAttributeTypeValueService {


    private final AttributeTypeValueRepository attributeTypeValue;
    private final AttributeTypeValueMapping attributeTypeValuemapping;

    public List<AttributeTypeValueResponseDTO> buscartodosvivos   () {

        return  attributeTypeValue.findAllByDeletedFalse().stream()
                .map(attributeTypeValuemapping::toDTO)
                .toList();
    }

    @Override
    public List<AttributeTypeValueResponseDTO> buscartodosmuertos() {

        return  attributeTypeValue.findAllByDeletedTrue()
                .stream()
                .map(attributeTypeValuemapping::toDTO)
                .toList();
        
    }


    public AttributeTypeValueResponseDTO buscarporid(Long id) {

        return attributeTypeValue.findAllByDeletedFalse(id)
                .stream()
                .filter(a -> a.getId().equals(id))
                .map(attributeTypeValuemapping::toDTO)
                .findFirst().orElseThrow();

    }

    @Override
    public void eliminarAttribute(Long id) {

        AttributeTypeValueModel attributeEntity = attributeTypeValue.findById(id).orElseThrow();

        attributeEntity.setDeleted(true);
        attributeTypeValue.save(attributeEntity);

    }



    @Override
    public AttributeTypeValueResponseDTO aniadirA(AttributeTypeValueRequestDTO attribute) {


        AttributeTypeValueModel attributeEntity = attributeTypeValuemapping.toEntity(attribute);
        AttributeTypeValueResponseDTO guardada = attributeTypeValuemapping.toDTO(attributeTypeValue.save(attributeEntity));

        log.info("Atributo creado con exito ID: {}", attributeEntity.getId());

        return guardada;

    }

    @Override
    public AttributeTypeValueResponseDTO actualizar(Long id, AttributeTypeValueRequestDTO attribute) {

        if (attributeTypeValue.existsById(id)) {

            if (attribute.getId() != null && !id.equals(attribute.getId())) {
                log.error("Error: ID URL ({}) no coincide con ID JSON ({})", id, attribute.getId());
                throw new RuntimeException("El ID del cuerpo no coincide con el ID de la URL");
            }

            AttributeTypeValueModel attributeEntity = attributeTypeValuemapping.toEntity(attribute);
            AttributeTypeValueResponseDTO guardada = attributeTypeValuemapping.toDTO(attributeTypeValue.save(attributeEntity));

            return guardada;
        } else {

            log.error("id no existente {}", id);
            throw new RuntimeException("El ID del cuerpo no existente");
        }


    }

}
