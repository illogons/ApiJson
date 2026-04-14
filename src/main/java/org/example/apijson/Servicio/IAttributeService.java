package org.example.apijson.Servicio;


import org.example.apijson.DTO.AttributeRequestDTO;
import org.example.apijson.DTO.AttributeResponseDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IAttributeService  {

    List<AttributeResponseDTO> buscartodosvivos();
    List<AttributeResponseDTO> buscartodosmuertos ();

    AttributeResponseDTO aniadirA(AttributeRequestDTO attribute) throws InvocationTargetException, IllegalAccessException;

    AttributeResponseDTO buscarporid(Long id);

    void eliminarAttribute(Long id);

    AttributeResponseDTO actualizarA(Long id, AttributeRequestDTO dto) throws InvocationTargetException, IllegalAccessException;
}
