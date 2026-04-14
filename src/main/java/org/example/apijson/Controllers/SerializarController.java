package org.example.apijson.Controllers;


import lombok.AllArgsConstructor;
import org.example.apijson.Servicio.DeserializarService;
import org.example.apijson.Servicio.SerializarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Serializar")
@AllArgsConstructor
public class SerializarController {

    private final SerializarService serializarService;
    private final DeserializarService deserializarService;

    @GetMapping("/serializar")
    public ResponseEntity<String> serializar(){
        return ResponseEntity.ok(serializarService.generarJson());

    }

    @PostMapping("/deserializar")
    public ResponseEntity<String> deserializar(@RequestBody String j) throws Exception{
        try{

            deserializarService.importarJson(j);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("¡El JSON se ha importado y guardado en la base de datos correctamente!");

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }



}
