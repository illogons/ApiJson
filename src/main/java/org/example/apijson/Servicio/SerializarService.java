package org.example.apijson.Servicio;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apijson.Model.ConfigModel;
import org.example.apijson.Repository.ConfigRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
@Slf4j
public class SerializarService {

    private final ConfigRepository configRepository;
    // el objectmapper es la fabrica de json
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public String generarJson() {
        try {


            // createObjectNode() crea las llaves principales del archivo: { }
            ObjectNode rootNode = objectMapper.createObjectNode();

            // nos devuelve una lista plana con todos los regisntros de config
            List<ConfigModel> configs = configRepository.findAll();// el JsonNode es el que nos permite añadir tanto un objeto como un array
            //el long es el id de la base de datos, y el valor son las cajas vacias
            Map<Long, JsonNode> contenedores = new HashMap<>();

            //Crear cajas, en este bucle solo importan los registros q son de tipo node, osea q contienen otros dentro
            for (ConfigModel config : configs) {
                String tipo = config.getAttribute().getAtributeType().getType(); // cogemos el tipo de atributtetype
                Boolean esLista = config.getAttribute().getAtributeType().getIsList(); // vemos si es una lista o no

                // Si es un "NODE", sabemos que es un contenedor (un objeto o una lista)
                if ("NODE".equals(tipo)) {
                    if (Boolean.TRUE.equals(esLista)) {
                        contenedores.put(config.getId(), objectMapper.createArrayNode());// Si es lista, fabricamos corchetes: [ ]
                    } else {
                        contenedores.put(config.getId(), objectMapper.createObjectNode());// Si es un objeto normal, fabricamos llaves: { }
                    }
                }
            }// al terminal este bucle tenemos un monton de [] y {} vacias solo identificados por su id,


            // SEGUNDO BUCLE: Rellenar cajas este bucle va a buscar con el padre en donde tiene q guardarse cada informacion
            for (ConfigModel config : configs) {
                // 1. Obtener metadatos básicos
                String nombre = config.getAttribute().getName();
                String tipo = config.getAttribute().getAtributeType().getType();
                String valor = config.getDefaultValue();
                Boolean esLista = config.getAttribute().getAtributeType().getIsList();

                // 2. Localizar al padre (si no tiene, es el root)
                JsonNode padreJson = (config.getParentConfig() == null) ? rootNode : contenedores.get(config.getParentConfig().getId());

                if (padreJson == null) continue;

                // 3. Decidir QUÉ vamos a meter (un Nodo o un Valor)
                JsonNode contenidoAMeter;

                if ("NODE".equals(tipo)) {
                    // Es una caja (objeto/array vacía) que ya creamos en el primer bucle
                    contenidoAMeter = contenedores.get(config.getId());
                } else {
                    // Es un dato final (String, Number, Boolean o Lista de ellos)
                    contenidoAMeter = transformarValorANodo(tipo, valor, esLista);
                }

                // 4. Vincularlo al padre (el méto do se encarga de saber si el padre es [] o {})
                vincularAlPadre(padreJson, nombre, contenidoAMeter);
            }

            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);

        } catch (Exception e) {
            // Imprimimos el error real en la consola de Spring Boot para saber qué falla
            e.printStackTrace();
            throw new RuntimeException("Error al generar el archivo JSON iterativo", e);
        }
    }

    // Estos métodos leen qué 'tipo' dice la base de datos que es, y lo convierten al tipo real en Java.

    private JsonNode transformarValorANodo(String tipo, String valor, Boolean esLista) {
        if (Boolean.TRUE.equals(esLista)) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            if (valor != null && !valor.isEmpty()) {
                String[] trozos = valor.split(";");
                for (String t : trozos) {
                    convertirYAgregar(arrayNode, tipo, t.trim());
                }
            }
            return arrayNode;
        } else {
            // Si no es lista, creamos un nodo de valor simple usando un ObjectNode temporal
            // para aprovechar la lógica de conversión.
            return convertirANodoSimple(tipo, valor);
        }
    }

    private void vincularAlPadre(JsonNode padre, String nombre, JsonNode contenido) {
        if (padre.isArray()) {
            ((ArrayNode) padre).add(contenido);
        } else {
            ((ObjectNode) padre).set(nombre, contenido);
        }
    }

    private void convertirYAgregar(ArrayNode array, String tipo, String v) {
        if (v == null || v.isEmpty()) { array.addNull(); return; }
        try {
            switch (tipo) {
                case "NUMERIC" -> array.add(Double.parseDouble(v));
                case "BOOLEAN" -> array.add(Boolean.parseBoolean(v));
                default -> array.add(v);
            }
        } catch (Exception e) { array.add(v); }
    }

    private JsonNode convertirANodoSimple(String tipo, String v) {
        if (v == null || v.isEmpty()) return objectMapper.nullNode();
        try {
            return switch (tipo) {
                case "NUMERIC" -> objectMapper.getNodeFactory().numberNode(Double.parseDouble(v));
                case "BOOLEAN" -> objectMapper.getNodeFactory().booleanNode(Boolean.parseBoolean(v));
                default -> objectMapper.getNodeFactory().textNode(v);
            };
        } catch (Exception e) {
            return objectMapper.getNodeFactory().textNode(v);
        }
    }
}






