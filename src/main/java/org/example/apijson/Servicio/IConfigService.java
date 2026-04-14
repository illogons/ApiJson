package org.example.apijson.Servicio;

import org.example.apijson.DTO.ConfigDTO;

import java.util.List;

public interface IConfigService {

    List<ConfigDTO> listar();
    ConfigDTO aniadir(ConfigDTO configDTO);
    void eliminar(Long id);
    ConfigDTO actualizar(Long id, ConfigDTO configDTO);



}
