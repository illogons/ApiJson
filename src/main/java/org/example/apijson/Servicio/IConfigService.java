package org.example.apijson.Servicio;

import org.example.apijson.DTO.ConfigRequestDto;
import org.example.apijson.DTO.ConfigResponseDTO;

import java.util.List;

public interface IConfigService {

    List<ConfigResponseDTO> listar();
    ConfigResponseDTO aniadir(ConfigRequestDto configDTO);
    void eliminar(Long id);
    ConfigResponseDTO actualizar(Long id, ConfigRequestDto configDTO);



}
