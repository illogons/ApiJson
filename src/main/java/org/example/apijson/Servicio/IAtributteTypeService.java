package org.example.apijson.Servicio;

import org.example.apijson.DTO.AttributeTypeReponseDTO;
import org.example.apijson.DTO.AttributeTypeRequestDTO;
import org.example.apijson.DTO.AttributeTypeValueRequestDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IAtributteTypeService {

    AttributeTypeReponseDTO listarid(Long id);

    List<AttributeTypeReponseDTO> listartodosvivos();
    List<AttributeTypeReponseDTO> buscartodosmuertos ();


    AttributeTypeReponseDTO aniadir(AttributeTypeRequestDTO dto) throws InvocationTargetException, IllegalAccessException;

    void eliminar(Long id);

    AttributeTypeReponseDTO actualizar(Long id, AttributeTypeRequestDTO dto);

}
