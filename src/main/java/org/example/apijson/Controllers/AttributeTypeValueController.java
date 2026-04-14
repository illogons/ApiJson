package org.example.apijson.Controllers;

import lombok.AllArgsConstructor;
import org.example.apijson.DTO.AttributeTypeValueRequestDTO;
import org.example.apijson.DTO.AttributeTypeValueResponseDTO;
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
    public ResponseEntity<AttributeTypeValueResponseDTO> listar(@PathVariable Long id){
        return ResponseEntity.ok(attributeTypeValueService.buscarporid(id));
    }

    @PostMapping("/guardarnuevo")
    public ResponseEntity<AttributeTypeValueResponseDTO> guardar(@RequestBody AttributeTypeValueRequestDTO DTO){
        return ResponseEntity.ok(attributeTypeValueService.aniadirA(DTO));
    }

    @GetMapping("/listarvivos")
    public List<AttributeTypeValueResponseDTO> listar(){
        return attributeTypeValueService.buscartodosvivos();
    }

    @GetMapping("/listareliminados")
    public List<AttributeTypeValueResponseDTO> listarLiminados(){
        return attributeTypeValueService.buscartodosmuertos();
    }

    @PutMapping("/eliminar/{id}")
    public void eliminar(@RequestBody Long id ){
        attributeTypeValueService.eliminarAttribute(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AttributeTypeValueResponseDTO> actualizar(@RequestBody Long id, @PathVariable AttributeTypeValueRequestDTO attribute){
        return ResponseEntity.ok(attributeTypeValueService.actualizar(id, attribute));
    }
}
