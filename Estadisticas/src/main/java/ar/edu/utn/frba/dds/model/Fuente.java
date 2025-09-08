package ar.edu.utn.frba.dds.model;

import static org.springframework.web.client.RestClient.create;

import java.util.Set;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

/*
GET /colecciones
GET /colecciones/:id/hechos
#Hechos porProvincia porColeccion De una colección, ¿en qué provincia se agrupan la mayor cantidad de hechos reportados?
GET /Hechos
#Hechos porCategoria              ¿Cuál es la categoría con mayor cantidad de hechos reportados?
#Hechos porProvincia porCategoria ¿En qué provincia se presenta la mayor cantidad de hechos de una cierta categoría?
#Hechos porHora porCategoria      ¿A qué hora del día ocurren la mayor cantidad de hechos de una cierta categoría?
GET /solicitudes                  ¿Cuántas solicitudes de eliminación son spam?
*/
public class Fuente {
  private String baseUrl;

  public Set<HechoDTO> hechos() {
    ResponseEntity<Set<HechoDTO>> result = create("http://" + baseUrl + "/hechos")
        .get()
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {
        });
    return result.getBody();
  }
}
