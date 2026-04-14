package org.example.apijson.Servicio;

import org.example.apijson.DTO.AttributeDTO;
import org.example.apijson.DTO.AttributeTypeDTO;
import org.example.apijson.DTO.AttributeTypeValueDTO;
import org.example.apijson.Entity.AttributeTypeValueEntity;

import java.util.List;

public interface IAttributeTypeValueService {

    List<AttributeTypeValueDTO> buscartodosvivos();
    List<AttributeTypeValueDTO> buscartodosmuertos();

    AttributeTypeValueDTO aniadirA(AttributeTypeValueDTO attribute);

    AttributeTypeValueDTO buscarporid(Long id);

    void eliminarAttribute(Long id);

    AttributeTypeValueDTO actualizar (Long id, AttributeTypeValueDTO attribute);



}

