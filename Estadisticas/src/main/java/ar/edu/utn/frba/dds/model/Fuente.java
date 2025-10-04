package ar.edu.utn.frba.dds.model;

import static jakarta.persistence.GenerationType.IDENTITY;
import static org.springframework.web.client.RestClient.create;
import ar.edu.utn.frba.dds.web.ColeccionDTO;
import ar.edu.utn.frba.dds.web.HechoDTO;
import ar.edu.utn.frba.dds.web.SolicitudDeEliminacionDeHechoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.Set;

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
@Entity
@Table(name = "FUENTE")
public class Fuente {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column(name = "BASE_URL", nullable = false)
  private String baseUrl;

  public Set<HechoDTO> hechos() {
    ResponseEntity<Set<HechoDTO>> result = create("http://" + baseUrl + "/hechos")
        .get()
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {
        });
    return result.getBody();
  }

  public Set<ColeccionDTO> colecciones() {
    ResponseEntity<Set<ColeccionDTO>> result = create("http://" + baseUrl + "/colecciones")
        .get()
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {
        });
    return result.getBody();
  }

  public Set<HechoDTO> coleccion(String coleccionHandleId) {
    ResponseEntity<Set<HechoDTO>> result = create("http://" + baseUrl + "/colecciones/" + coleccionHandleId + "/hechos")
        .get()
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {
        });
    return result.getBody();
  }

  public Set<SolicitudDeEliminacionDeHechoDTO> solicitudes() {
    ResponseEntity<Set<SolicitudDeEliminacionDeHechoDTO>> result = create("http://" + baseUrl + "/solicitudes/")
        .get()
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {
        });
    return result.getBody();
  }
}
