package org.example.apijson.Servicio;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.DTO.AttributeRequestDTO;
import org.example.apijson.DTO.AttributeResponseDTO;
import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Mapping.AttributeMapping;
import org.example.apijson.Repository.AttributeRepository;
import org.example.apijson.Repository.AttributeTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AttributeService implements IAttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeTypeRepository attributeTypeRepository;
    private final AttributeMapping attributeMapping;

    @SneakyThrows
    public List<AttributeResponseDTO> buscartodosvivos() {
        return attributeRepository.findAllByDeletedFalse().stream()
                .map(attributeMapping::toDTO)
                .toList();
    }

    @Override
    public List<AttributeResponseDTO> buscartodosmuertos(){
        return attributeRepository.findAllByDeletedTrue().stream()
                .map(attributeMapping::toDTO)
                .toList();
    }

    @SneakyThrows // <--- AÑADE ESTO AQUÍ
    @Override
    public AttributeResponseDTO aniadirA(AttributeRequestDTO attribute) throws InvocationTargetException, IllegalAccessException {

        // Corregido: attributeMapping en minúscula
        AttributeEntity attributeEntity = attributeMapping.toEntity(attribute);

        AttributeResponseDTO guardada = attributeMapping.toDTO(attributeRepository.save(attributeEntity));

        log.info("Atributo creado con exito ID: {}", attributeEntity.getId());

        return guardada;
    }

    public AttributeResponseDTO buscarporid(Long id) {
        // Corregido: Le quitamos el (id) al findAllByDeletedFalse para que no explote al iniciar
        return attributeRepository.findAllByDeletedFalse()
                .stream()
                .filter(a -> a.getId().equals(id))
                .map(attributeMapping::toDTO)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró el atributo con ID: " + id));
    }

    @Override
    public void eliminarAttribute(Long id) {
        AttributeEntity attributeEntity = attributeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atributo no encontrado"));

        attributeEntity.setDeleted(true);
        attributeRepository.save(attributeEntity);
    }

    @Transactional
    @Override
    public AttributeResponseDTO actualizarA(Long id, AttributeRequestDTO dto) { // Añadido el ID como parámetro

        // 1. Buscamos la entidad que ya existe
        AttributeEntity entityExistente = attributeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atributo no encontrado con ID: " + id));

        // 2. Actualizamos los campos necesarios manualmente
        entityExistente.setName(dto.getName());

        // (Si el DTO trae un nuevo tipo de atributo, lo asignamos aquí)
        // if(dto.getAttributeTypeId() != null) {
        //     AttributeTypeEntity type = new AttributeTypeEntity();
        //     type.setId(dto.getAttributeTypeId());
        //     entityExistente.setAtributeType(type);
        // }

        // 3. Guardamos la entidad existente ya modificada
        AttributeResponseDTO guardada = attributeMapping.toDTO(attributeRepository.save(entityExistente));

        log.info("Atributo actualizado con exito ID: {}", id);

        return guardada;
    }
}