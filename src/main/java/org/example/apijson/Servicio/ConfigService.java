package org.example.apijson.Servicio;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.DTO.ConfigDTO;
import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Entity.ConfigEntity;
import org.example.apijson.Mapping.AttributeMapping;
import org.example.apijson.Mapping.ConfigMapping;
import org.example.apijson.Repository.AttributeRepository;
import org.example.apijson.Repository.ConfigRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ConfigService implements IConfigService {

    private final ConfigRepository configRepository;
    private final AttributeRepository attributeRepository;
    private final ConfigMapping configMapping;
    private final AttributeMapping attributeMapping;


    @Override
    public List<ConfigDTO> listar() {
        return configRepository.findAll()
                .stream()
                .map(configMapping::toDTO)
                .toList();
    }

    @Override
    public ConfigDTO aniadir(ConfigDTO configDTO) {

        AttributeEntity attributeEntity = attributeRepository
                .findById(configDTO.getAttributeId()).orElseThrow(() -> new RuntimeException("Tipo de atributo no encontrado"));

        ConfigEntity parent = null;
        if(parent.getParentConfig() != null) {
            parent = configRepository.findById(configDTO.getParent()).orElseThrow(() -> new RuntimeException("Tipo de atributo no encontrado"));
        }

        ConfigEntity configEntity = configMapping.toEntity(configDTO,attributeEntity,parent);
        ConfigDTO dto = configMapping.toDTO(configRepository.save(configEntity));

        return dto;
    }

    @Override
    public void eliminar(Long id){

        ConfigEntity configid= configRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo de atributo no encontrado"));

        configid.setDeleted(true);
        configRepository.save(configid);
    }

    @Override
    public ConfigDTO actualizar(Long id, ConfigDTO configDTO) {

        if(configRepository.existsById(id)){

            if (configDTO.getId() != null && !id.equals(configDTO.getId())) {
                log.error("Error: ID URL ({}) no coincide con ID JSON ({})", id, configDTO.getId());
                throw new RuntimeException("El ID del cuerpo no coincide con el ID de la URL");
            }

            ConfigEntity configentity = configRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo de atributo no encontrado"));

            AttributeEntity attributeEntity = attributeRepository
                    .findById(configDTO.getAttributeId()).orElseThrow(() -> new RuntimeException("Tipo de atributo no encontrado"));

            ConfigEntity parent = null;
            if(parent.getParentConfig() != null) {
                parent = configRepository.findById(configDTO.getParent()).orElseThrow(() -> new RuntimeException("Tipo de atributo no encontrado"));
            }

            configentity.setDefaultValue(configDTO.getDefaultValue());
            configentity.setAttribute(attributeEntity);
            configentity.setParentConfig(parent);

            ConfigEntity configEntity = configMapping.toEntity(configDTO,attributeEntity,parent);
            ConfigDTO dto = configMapping.toDTO(configRepository.save(configEntity));


            return dto;

        }else{
            log.error("id no existente {}", id );
            throw new RuntimeException("El ID del cuerpo no existente");
        }
}

}


