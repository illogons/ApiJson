package org.example.apijson.Servicio;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.DTO.AttributeTypeValueDTO;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.example.apijson.Entity.AttributeTypeValueEntity;
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

    public List<AttributeTypeValueDTO> buscartodosvivos   () {

        return  attributeTypeValue.findAllByDeletedFalse().stream()
                .map(attributeTypeValuemapping::toDTO)
                .toList();
    }

    @Override
    public List<AttributeTypeValueDTO> buscartodosmuertos() {

        return  attributeTypeValue.findAllByDeletedTrue()
                .stream()
                .map(attributeTypeValuemapping::toDTO)
                .toList();
        
    }


    public AttributeTypeValueDTO buscarporid(Long id) {

        return attributeTypeValue.findAllByDeletedFalse(id)
                .stream()
                .filter(a -> a.getId().equals(id))
                .map(attributeTypeValuemapping::toDTO)
                .findFirst().orElseThrow();

    }

    @Override
    public void eliminarAttribute(Long id) {

        AttributeTypeValueEntity attributeEntity = attributeTypeValue.findById(id).orElseThrow();

        attributeEntity.setDeleted(true);
        attributeTypeValue.save(attributeEntity);

    }



    @Override
    public AttributeTypeValueDTO aniadirA(AttributeTypeValueDTO attribute) {

        AttributeTypeEntity atributetype = attributeTypeValue.findById(attribute.getAttributeTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de atributo no encontrado")).getAttributeType();

        AttributeTypeValueEntity attributeEntity = attributeTypeValuemapping.toEntity(attribute, atributetype);
        AttributeTypeValueDTO guardada = attributeTypeValuemapping.toDTO(attributeTypeValue.save(attributeEntity));

        log.info("Atributo creado con exito ID: {} | Typo correcto: {}", attributeEntity.getId(), atributetype.getType());

        return guardada;

    }

    @Override
    public AttributeTypeValueDTO actualizar(Long id, AttributeTypeValueDTO attribute) {

        if (attributeTypeValue.existsById(id)) {

            if (attribute.getId() != null && !id.equals(attribute.getId())) {
                log.error("Error: ID URL ({}) no coincide con ID JSON ({})", id, attribute.getId());
                throw new RuntimeException("El ID del cuerpo no coincide con el ID de la URL");
            }

            AttributeTypeEntity atributetype = attributeTypeValue.findById(attribute.getAttributeTypeId())
                    .orElseThrow(() -> new RuntimeException("Tipo de atributo no encontrado")).getAttributeType();

            AttributeTypeValueEntity attributeEntity = attributeTypeValuemapping.toEntity(attribute, atributetype);
            AttributeTypeValueDTO guardada = attributeTypeValuemapping.toDTO(attributeTypeValue.save(attributeEntity));

            return guardada;
        } else {

            log.error("id no existente {}", id);
            throw new RuntimeException("El ID del cuerpo no existente");
        }


    }

}
