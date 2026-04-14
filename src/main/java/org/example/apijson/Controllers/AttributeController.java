package org.example.apijson.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.apijson.DTO.AttributeRequestDTO;
import org.example.apijson.DTO.AttributeResponseDTO;
import org.example.apijson.Servicio.IAttributeService;
import org.example.apijson.annotation.SearchOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/Attribute")
@RequiredArgsConstructor
public class AttributeController {
    private final IAttributeService attributeService;

    @GetMapping("/listar/{id}")
    @SearchOperation(summary = "hola")
    public ResponseEntity<AttributeResponseDTO> listar(@PathVariable Long id){
        return ResponseEntity.ok(attributeService.buscarporid(id));
    }

    @PostMapping("/guardarnuevo")
    public ResponseEntity<AttributeResponseDTO> guardar(@RequestBody AttributeRequestDTO attributeDTO) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(attributeService.aniadirA(attributeDTO));
    }

    @GetMapping("/listar")
    public List<AttributeResponseDTO> listar(){
        return attributeService.buscartodosvivos();

    }

    @GetMapping("/listareliminados")
    public List<AttributeResponseDTO> listarLiminados(){
        return attributeService.buscartodosmuertos();
    }

    @PutMapping("/eliminar/{id}")
    public void eliminar(@RequestBody Long id ){
        attributeService.eliminarAttribute(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AttributeResponseDTO> actualizar(@RequestBody Long id, @PathVariable AttributeRequestDTO attribute) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(attributeService.actualizarA(id, attribute));
    }




    }
