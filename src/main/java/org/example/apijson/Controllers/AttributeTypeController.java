package org.example.apijson.Controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.DTO.AttributeTypeReponseDTO;
import org.example.apijson.DTO.AttributeTypeRequestDTO;
import org.example.apijson.DTO.AttributeTypeValueResponseDTO;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.example.apijson.Servicio.AttributeTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/AttributeType")
@Slf4j
@RequiredArgsConstructor
public class AttributeTypeController {

    private final AttributeTypeService attributeTypeService;

    @GetMapping("/listar/{id}")
    public ResponseEntity<AttributeTypeReponseDTO> listar(@PathVariable Long id){
        return ResponseEntity.ok(attributeTypeService.listarid(id));
    }
    @GetMapping("/listar")
    public ResponseEntity<List<AttributeTypeReponseDTO>> listar(){
        return ResponseEntity.ok(attributeTypeService.listartodosvivos());
    }

    @PostMapping("/añadir")
    public AttributeTypeReponseDTO añadir(@RequestBody AttributeTypeRequestDTO dto) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(attributeTypeService.aniadir(dto)).getBody();
    }

    @GetMapping("/listareliminados")
    public List<AttributeTypeReponseDTO> listarLiminados(){
        return attributeTypeService.buscartodosmuertos();
    }

    @PutMapping("/eliminar/{id}")
    public void eliminar(@RequestBody Long id ){
        attributeTypeService.eliminar(id);
    }



}
