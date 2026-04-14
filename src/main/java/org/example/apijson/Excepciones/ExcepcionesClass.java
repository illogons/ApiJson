package org.example.apijson.Excepciones;


import org.apache.coyote.BadRequestException;
import org.example.apijson.DTO.AttributeTypeValueDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExcepcionesClass {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcepcionesClass.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "URL_NOT_FOUND" +  ex.getMessage());
        error.put("detalle", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonException(HttpMessageNotReadableException ex) {
        LOGGER.error("Error leyendo JSON: {}", ex.getMessage());

        Map<String, Object> error = new HashMap<>();
        error.put("error", "JSON mal formado o inválido");
        // getMostSpecificCause() nos da el error exacto (ej: no se puede convertir String a Long)
        error.put("detalle", ex.getMostSpecificCause().getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeExceptions(RuntimeException ex) {
        LOGGER.error("Error de lógica de negocio: {}", ex.getMessage());

        Map<String, Object> error = new HashMap<>();
        error.put("error", "Error en la petición");
        error.put("detalle", ex.getMessage()); // Aquí saldrá "Configuración padre no encontrada", etc.

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }





}
