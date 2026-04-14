package org.example.apijson.Controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.DTO.AttributeTypeDTO;
import org.example.apijson.DTO.AttributeTypeValueDTO;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.example.apijson.Servicio.AttributeTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AttributeType")
@Slf4j
@RequiredArgsConstructor
public class AttributeTypeController {

    private final AttributeTypeService attributeTypeService;

    @GetMapping("/listar/{id}")
    public ResponseEntity<AttributeTypeDTO> listar(@PathVariable Long id){
        return ResponseEntity.ok(attributeTypeService.listarid(id));
    }
    @GetMapping("/listar")
    public ResponseEntity<List<AttributeTypeDTO>> listar(){
        return ResponseEntity.ok(attributeTypeService.listartodosvivos());
    }

    @PostMapping("/añadir")
    public AttributeTypeDTO añadir(@RequestBody AttributeTypeDTO attributeTypeDTO){
        return ResponseEntity.ok(attributeTypeService.aniadir(attributeTypeDTO)).getBody();
    }

    @GetMapping("/listareliminados")
    public List<AttributeTypeDTO> listarLiminados(){
        return attributeTypeService.buscartodosmuertos();
    }

    @PutMapping("/eliminar/{id}")
    public void eliminar(@RequestBody Long id ){
        attributeTypeService.eliminar(id);
    }



}
