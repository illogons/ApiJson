package org.example.apijson.Controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.DTO.ConfigDTO;
import org.example.apijson.Entity.ConfigEntity;
import org.example.apijson.Servicio.ConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("Config")
public class ConfigController{

    private final ConfigService configService;

    @GetMapping("/listar")
    public ResponseEntity<List<ConfigDTO>> listar(){
        return  ResponseEntity.ok(configService.listar());
    }

    @PostMapping("/generarJson")
    public ResponseEntity<ConfigDTO> aniadir(@RequestBody ConfigDTO configDTO){
        return  ResponseEntity.ok(configService.aniadir(configDTO));

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ConfigDTO> actualizar(@RequestBody Long id, @PathVariable ConfigDTO configDTO){
        return ResponseEntity.ok(configService.actualizar(id, configDTO));
    }

    @PutMapping("/delete/{id}")
    public void eliminar(@RequestBody Long id){
        configService.eliminar(id);
    }


    }


