package org.example.apijson.Controllers;

import lombok.AllArgsConstructor;
import org.example.apijson.DTO.AttributeDTO;
import org.example.apijson.DTO.AttributeTypeValueDTO;
import org.example.apijson.DTO.ConfigDTO;
import org.example.apijson.Entity.AttributeTypeValueEntity;
import org.example.apijson.Servicio.IAttributeService;
import org.example.apijson.Servicio.IAttributeTypeValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AttributeTypeValue")
public class AttributeTypeValueController {

    private final IAttributeTypeValueService attributeTypeValueService;

    @GetMapping("/listar/{id}")
    public ResponseEntity<AttributeTypeValueDTO> listar(@PathVariable Long id){
        return ResponseEntity.ok(attributeTypeValueService.buscarporid(id));
    }

    @PostMapping("/guardarnuevo")
    public ResponseEntity<AttributeTypeValueDTO> guardar(@RequestBody AttributeTypeValueDTO DTO){
        return ResponseEntity.ok(attributeTypeValueService.aniadirA(DTO));
    }

    @GetMapping("/listarvivos")
    public List<AttributeTypeValueDTO> listar(){
        return attributeTypeValueService.buscartodosvivos();
    }

    @GetMapping("/listareliminados")
    public List<AttributeTypeValueDTO> listarLiminados(){
        return attributeTypeValueService.buscartodosmuertos();
    }

    @PutMapping("/eliminar/{id}")
    public void eliminar(@RequestBody Long id ){
        attributeTypeValueService.eliminarAttribute(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AttributeTypeValueDTO> actualizar(@RequestBody Long id, @PathVariable AttributeTypeValueDTO attribute){
        return ResponseEntity.ok(attributeTypeValueService.actualizar(id, attribute));
    }
}
