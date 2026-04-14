package org.example.apijson.Servicio;


import org.example.apijson.DTO.AttributeDTO;
import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Entity.AttributeTypeValueEntity;

import java.util.List;

public interface IAttributeService  {

    List<AttributeDTO> buscartodosvivos();
    List<AttributeDTO> buscartodosmuertos ();

    AttributeDTO aniadirA(AttributeDTO attribute);

    AttributeDTO buscarporid(Long id);

    void eliminarAttribute(Long id);

    AttributeDTO actualizarA(Long id, AttributeDTO dto);

}
