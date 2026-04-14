package org.example.apijson.Servicio;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.DTO.AttributeTypeReponseDTO;
import org.example.apijson.DTO.AttributeTypeRequestDTO;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.example.apijson.Mapping.AttributeTypeMapping;
import org.example.apijson.Repository.AttributeTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@AllArgsConstructor
@Slf4j
public class AttributeTypeService implements IAtributteTypeService {
    
    private final AttributeTypeRepository attributeTypeRepository;
    private final AttributeTypeMapping attributeTypeMapping;


    @Override
    @Transactional(readOnly = true)
    public AttributeTypeReponseDTO listarid(Long id) {

        return attributeTypeRepository.findIdByDeletedFalse(id)
                .stream()
                .filter(a -> a.getId().equals(id))
                .map(attributeTypeMapping::toDTO)
                .findFirst().orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttributeTypeReponseDTO> listartodosvivos() {
        return attributeTypeRepository.findAllByDeletedFalse()
                .stream()
                .map(attributeTypeMapping::toDTO)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public List<AttributeTypeReponseDTO> buscartodosmuertos() {
        return  attributeTypeRepository.findAllByDeletedTrue()
                .stream()
                .map(attributeTypeMapping::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AttributeTypeReponseDTO aniadir(AttributeTypeRequestDTO dto) throws InvocationTargetException, IllegalAccessException {
        if(attributeTypeRepository.existsById(dto.getId())){
        log.error("ya existe este id");
        throw new RuntimeException("ya existe este id");
        }

        AttributeTypeEntity attributeTypeEntity = AttributeTypeMapping.toEntity(dto);
        AttributeTypeReponseDTO guardado = attributeTypeMapping.toDTO(attributeTypeRepository.save(attributeTypeEntity));
        log.info("guardado: {}", guardado);

        return guardado;


    }

    @Override
    @Transactional(readOnly = true)
    public void eliminar(Long id) {

        AttributeTypeEntity attributeTypeEntity = attributeTypeRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        attributeTypeEntity.setDeleted(true);
        attributeTypeRepository.save(attributeTypeEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public AttributeTypeReponseDTO actualizar(Long id, AttributeTypeRequestDTO dto){

        if (dto.getId() != null && !id.equals(dto.getId())) {
            log.error("Error: ID URL ({}) no coincide con ID JSON ({})", id, dto.getId());
            throw new RuntimeException("El ID del cuerpo no coincide con el ID de la URL");
        }

        AttributeTypeEntity attributeTypeEntity = attributeTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el tipo de atributo con ID: " + id));

        // 3. Actualizamos los datos
        attributeTypeEntity.setType(dto.getType());
        attributeTypeEntity.setIsList(dto.getIsList());
        attributeTypeEntity.setIsEnum(dto.getIsEnum());

        // 4. Guardamos en base de datos
        attributeTypeRepository.save(attributeTypeEntity);

        // 5. Convertimos a ResponseDTO y lo devolvemos
        return attributeTypeMapping.toDTO(attributeTypeEntity);
    }


}
