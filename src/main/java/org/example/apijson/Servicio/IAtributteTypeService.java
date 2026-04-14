package org.example.apijson.Servicio;

import org.example.apijson.DTO.AttributeDTO;
import org.example.apijson.DTO.AttributeTypeDTO;
import org.example.apijson.Entity.AttributeTypeEntity;

import java.util.List;

public interface IAtributteTypeService {

    AttributeTypeDTO listarid(Long id);

    List<AttributeTypeDTO> listartodosvivos();
    List<AttributeTypeDTO> buscartodosmuertos ();


    AttributeTypeDTO aniadir(AttributeTypeDTO dto);

    void eliminar(Long id);

    AttributeTypeDTO actualizar(Long id, AttributeTypeDTO dto);


}
