package org.example.apijson.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeDTO;
import org.example.apijson.DTO.AttributeTypeValueDTO;
import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Servicio.IAttributeService;
import org.example.apijson.annotation.SearchOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Attribute")
@RequiredArgsConstructor
public class AttributeController {
    private final IAttributeService attributeService;

    @GetMapping("/listar/{id}")
    @SearchOperation(summary = "hola")
    public ResponseEntity<AttributeDTO> listar(@PathVariable Long id){
        return ResponseEntity.ok(attributeService.buscarporid(id));
    }

    @PostMapping("/guardarnuevo")
    public ResponseEntity<AttributeDTO> guardar(@RequestBody AttributeDTO attributeDTO){
        return ResponseEntity.ok(attributeService.aniadirA(attributeDTO));
    }

    @GetMapping("/listar")
    public List<AttributeDTO> listar(){
        return attributeService.buscartodosvivos();

    }

    @GetMapping("/listareliminados")
    public List<AttributeDTO> listarLiminados(){
        return attributeService.buscartodosmuertos();
    }

    @PutMapping("/eliminar/{id}")
    public void eliminar(@RequestBody Long id ){
        attributeService.eliminarAttribute(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AttributeDTO> actualizar(@RequestBody Long id, @PathVariable AttributeDTO attribute){
        return ResponseEntity.ok(attributeService.actualizarA(id, attribute));
    }




    }
