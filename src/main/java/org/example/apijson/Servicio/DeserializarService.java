package org.example.apijson.Servicio;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.example.apijson.Entity.AttributeTypeValueEntity;
import org.example.apijson.Entity.ConfigEntity;
import org.example.apijson.Repository.AttributeRepository;
import org.example.apijson.Repository.AttributeTypeRepository;
import org.example.apijson.Repository.AttributeTypeValueRepository;
import org.example.apijson.Repository.ConfigRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j // Para imprimir los errores sin que explote
public class DeserializarService {

    private final ConfigRepository configRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeTypeRepository typeRepository;
    private final AttributeTypeValueRepository attributeTypeValueRepository;// añado este ahora para comprobar los enum
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Transactional// medida de seguridad
    public void importarJson(String jsonString) throws Exception {


        JsonNode root = objectMapper.readTree(jsonString);

        root.fields().forEachRemaining(entry -> {

            procesarNodo(entry.getKey(), entry.getValue(), null);

        });
    }

    private void procesarNodo(String nombreAtributo, JsonNode nodoActual, ConfigEntity padre) {
        try {
            // buscamos si el atributo existe o le tenemos q crear
            AttributeEntity atributo = gestionarAtributo(nombreAtributo, nodoActual);

            ConfigEntity config;
            Optional<ConfigEntity> configExistente;


            if (padre == null) {
                configExistente = configRepository.findByAttribute_IdAndParentConfigIsNull(atributo.getId());// buscamos si existe con padre nulo
            } else {
                configExistente = configRepository.findByAttribute_IdAndParentConfig_Id(atributo.getId(), padre.getId());// buscamos si existe con padre
            }

            // aqui es donde actualizo o creo
            if (configExistente.isPresent()) {

                config = configExistente.get();
                config.setModifiedAt(LocalDateTime.now());
            } else {

                config = new ConfigEntity();
                config.setAttribute(atributo);
                config.setParentConfig(padre); // Le asigno su padre
                config.setCreatedAt(LocalDateTime.now());
                config.setModifiedAt(LocalDateTime.now());
                config.setDeleted(false);
                config.setVersionLock(0L);
                config.setIsCustom(false);
            }
            if (nodoActual.isObject()) {

                config = configRepository.save(config); // lo guardo para q genere su propio id
                ConfigEntity padreActual = config; // Esta carpeta ahora se convierte en el "padre" de lo que lleve dentro

                // Recorremos tod0 lo que hay dentro de esta carpeta y volvemos a llamar a este mismo métod0
                nodoActual.fields().forEachRemaining(entry ->
                        procesarNodo(entry.getKey(), entry.getValue(), padreActual)
                );

            } else if (nodoActual.isArray()) {

                if (nodoActual.isEmpty() || (nodoActual.get(0) != null && nodoActual.get(0).isValueNode())) {
                    List<String> valores = new ArrayList<>();
                    nodoActual.forEach(nodo -> valores.add(nodo.asText())); // paso tod0 a texto

                    String valorUnido = String.join(";", valores);

                    validarvalues(nombreAtributo, valorUnido);// antes de guardar valida

                    config.setDefaultValue(valorUnido); // Metemos el valor juntado en la caja
                    configRepository.save(config); // Guardamos o actualizamos en la BD
                } else {
                    // Es una lista compleja (una lista que contiene objetos dentro).
                    config = configRepository.save(config); // Guardamos el padre
                    ConfigEntity padreActual = config;
                    // Recorremos la lista y procesamos cada elemento añadiéndole "_ITEM" al nombre
                    nodoActual.forEach(itemJson ->
                            procesarNodo(nombreAtributo + "_ITEM", itemJson, padreActual)
                    );
                }

            } else {
                // si es un unico valor

                String valorSimple = nodoActual.asText();

                validarvalues(nombreAtributo, valorSimple);

                config.setDefaultValue(valorSimple);
                configRepository.save(config);
            }

        } catch (RuntimeException e) {
            throw e;

        } catch (Exception e) {
            log.error("Error en BD procesando el nodo '{}': {}", nombreAtributo, e.getMessage());
            throw new RuntimeException("Error procesando '" + nombreAtributo + "': " + e.getMessage());
        }
    }


    private AttributeEntity gestionarAtributo(String nombre, JsonNode nodo) {
        //  Buscamos si ya existe el atributo en BD
        Optional<AttributeEntity> atributoExistente = attributeRepository.findByName(nombre);
        if (atributoExistente.isPresent()) {
            return atributoExistente.get();
        }

        //  Si no existe el Atributo, primero deducimos qué TIPO vamos a necesitar
        String expectedType = "STRING";
        Boolean expectedIsList = false;
        Boolean expectedIsEnum = false;

        if (nodo.isObject()) {
            expectedType = "NODE";
        } else if (nodo.isArray()) {
            expectedIsList = true;
            if (!nodo.isEmpty() && nodo.get(0).isObject()) {
                expectedType = "NODE";
            } else if (!nodo.isEmpty() && nodo.get(0).isNumber()) {
                expectedType = "NUMERIC";
            } else {
                expectedType = "STRING";
            }
        } else if (nodo.isNumber()) {
            expectedType = "NUMERIC";
        } else if (nodo.isBoolean()) {
            expectedType = "BOOLEAN";
        }

        //  buscamos si ya existe
        AttributeTypeEntity tipoAsignar;
        Optional<AttributeTypeEntity> tipoExistente = typeRepository.findByTypeAndIsListAndIsEnum(expectedType, expectedIsList, expectedIsEnum);

        if (tipoExistente.isPresent()) {
            // El tipo ya existe, lo reciclamos.
            tipoAsignar = tipoExistente.get();
        } else {
            // si tipo no existe, lo creamos nuevo.
            AttributeTypeEntity nuevoTipo = new AttributeTypeEntity();
            nuevoTipo.setCreatedAt(LocalDateTime.now());
            nuevoTipo.setModifiedAt(LocalDateTime.now());
            nuevoTipo.setDeleted(false);
            nuevoTipo.setVersionLock(0L);

            nuevoTipo.setType(expectedType);
            nuevoTipo.setIsList(expectedIsList);
            nuevoTipo.setIsEnum(expectedIsEnum);

            tipoAsignar = typeRepository.save(nuevoTipo);
        }

        // Ahora sí, creamos el Atributo nuevo uniéndolo a su tipo nuevo o no
        AttributeEntity nuevoAtributo = new AttributeEntity();
        nuevoAtributo.setName(nombre);
        nuevoAtributo.setAtributeType(tipoAsignar); // Usamos el tipo correcto
        nuevoAtributo.setCreatedAt(LocalDateTime.now());
        nuevoAtributo.setModifiedAt(LocalDateTime.now());
        nuevoAtributo.setDeleted(false);
        nuevoAtributo.setVersionLock(0L);

        return attributeRepository.save(nuevoAtributo);
    }

    private void validarvalues(String nombreAtributo, String valorJson) {

        Optional<AttributeTypeEntity> tipoOpt = typeRepository.findByType(nombreAtributo.toUpperCase());// busco el tipo del atributo en este cao de
        // indicators y encuentra el INDICATORS

        if (tipoOpt.isPresent() && Boolean.TRUE.equals(tipoOpt.get().getIsEnum())) {// si no sale null y es enum entra

            Long typeId = tipoOpt.get().getId();// coge su id del tipo que es 10

            List<String> permitidos = obtenerValoresPermitidosPorTipo(typeId);// creo una lista pasandole el id 10 y
            // tod0 el q tenga ese id en atributtevalue lo coge

            if (!permitidos.isEmpty()) {
                String[] trozos = valorJson.split(";"); // hacemos un array de los valores del array q venian de antes y los splitemaos

                for (String trozo : trozos) {
                    String valorLimpio = trozo.trim(); // les leemos y les pasamos sin espacios en blanco

                    if (!permitidos.contains(valorLimpio)) {// si en la lista de permitidos con id 10, esta uno de los valores q venian de antes se coge
                        throw new RuntimeException("ERROR: El valor '" + valorLimpio +
                                "' no está permitido para '" + nombreAtributo +
                                "' opciones válidas: " + permitidos);//
                    }
                }
                log.info("validación exitosa para el atributo '{}'", nombreAtributo);
            }
        }
    }

    private List<String> obtenerValoresPermitidosPorTipo(Long typeId) {// va a buscar con el id del tipo todos los que sean de ese tipo
        return attributeTypeValueRepository.findByAttributeType_IdAndDeletedFalse(typeId)
                .stream()
                .map(AttributeTypeValueEntity::getValue)// esto va a coger solo el value/ el nombre
                .toList();
    }
}


