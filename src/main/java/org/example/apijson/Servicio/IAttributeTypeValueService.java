package org.example.apijson.Servicio;

import org.example.apijson.DTO.AttributeTypeValueRequestDTO;
import org.example.apijson.DTO.AttributeTypeValueResponseDTO;

import java.util.List;

public interface IAttributeTypeValueService {

    List<AttributeTypeValueResponseDTO> buscartodosvivos();
    List<AttributeTypeValueResponseDTO> buscartodosmuertos();

    AttributeTypeValueResponseDTO aniadirA(AttributeTypeValueRequestDTO attribute);

    AttributeTypeValueResponseDTO buscarporid(Long id);

    void eliminarAttribute(Long id);

    AttributeTypeValueResponseDTO actualizar (Long id, AttributeTypeValueRequestDTO attribute);



}

