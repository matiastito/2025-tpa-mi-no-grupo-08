package ar.edu.utn.frba.dds.modelo.fuente;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.DINAMICA;
import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.ESTATICA;
import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.METAMAPA;
import static ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO.toDTO;
import static ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO.toHecho;
import static ar.edu.utn.frba.dds.web.controlador.dto.SolicitudDeEliminacionDeHechoDTO.toDTO;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.util.stream.Collectors.toSet;
import static org.hibernate.annotations.CascadeType.ALL;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.web.client.RestClient.create;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import ar.edu.utn.frba.dds.normalizador.NormalizadorCategoria;
import ar.edu.utn.frba.dds.web.controlador.dto.ContribuyenteDTO;
import ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO;
import ar.edu.utn.frba.dds.web.controlador.dto.SolicitudDeEliminacionDeHechoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cascade;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

//FIXME Abastraer tres implemetacióones, una para cada fuente
@Entity
@Table(name = "FUENTE")
public class Fuente {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Enumerated(STRING)
  private TipoFuente tipoFuente;
  @Column(name = "BASE_URL", nullable = false)
  private String baseUrl;
  @OneToMany
  @JoinColumn(name = "FUENTE_ID")
  @Cascade(ALL)
  private final Set<Hecho> hechos = new HashSet<>();

  public Fuente() {
  }

  public Fuente(String baseUrl, TipoFuente tipoFuente) {
    this.baseUrl = baseUrl;
    this.tipoFuente = tipoFuente;
  }

  public Fuente(Long id, String baseUrl, TipoFuente tipoFuente) {
    this.id = id;
    this.baseUrl = baseUrl;
    this.tipoFuente = tipoFuente;
  }

  public long getId() {
    return id;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setTipoFuente(TipoFuente tipoFuente) {
    this.tipoFuente = tipoFuente;
  }

  public TipoFuente getTipoFuente() {
    return tipoFuente;
  }

  public void refrescar(NormalizadorCategoria normalizadorCategoria) {
    if (!METAMAPA.equals(tipoFuente)) {
      ResponseEntity<Set<HechoDTO>> result =
          create("http://" + baseUrl + "/hechos")
              .get()
              .retrieve()
              .toEntity(new ParameterizedTypeReference<>() {
              });
      this.hechos.addAll(result
          .getBody()
          .stream()
          .map(h -> normalizadorCategoria.normalizar(toHecho(h, this)))
          .collect(toSet()));
    }
  }

  public Set<Hecho> hechos() {
    return this.hechos;
  }

  public void eliminar(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho) {
    if (METAMAPA.equals(tipoFuente) || DINAMICA.equals(tipoFuente)) {
      SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO = toDTO(solicitudDeEliminacionDeHecho);
      solicitudDeEliminacionDeHechoDTO.setHechoId(solicitudDeEliminacionDeHecho.getHecho().getIdExterno());
      if (solicitudDeEliminacionDeHecho.getRepotador() != null) {
        solicitudDeEliminacionDeHechoDTO.setRepotador(new ContribuyenteDTO(solicitudDeEliminacionDeHecho.getRepotador().getNombre()));
      }
      create("http://" + baseUrl + "/solicitudes")
          .post()
          .body(toDTO(solicitudDeEliminacionDeHecho))
          .retrieve()
          .toEntity(new ParameterizedTypeReference<>() {
          });
    }
  }

  public void crear(Hecho hecho) {
    if (DINAMICA.equals(tipoFuente)) {
      create("http://" + baseUrl + "/hechos")
          .post()
          .body(toDTO(hecho))
          .retrieve()
          .toEntity(new ParameterizedTypeReference<>() {
          });
    }
  }

  public void editar(Hecho hecho) {
    if (DINAMICA.equals(tipoFuente)) {
      HechoDTO hechoDTO = toDTO(hecho);
      hechoDTO.setId(hecho.getIdExterno());
      create("http://" + baseUrl + "/hechos")
          .put()
          .body(hechoDTO)
          .retrieve()
          .toEntity(new ParameterizedTypeReference<>() {
          });
    }
  }

  public void importarHechos(MultipartFile archivoCSV) {
    if (ESTATICA.equals(tipoFuente)) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MULTIPART_FORM_DATA);
      MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
      body.add("archivoCSVDeHechos", archivoCSV.getResource());
      HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
      new RestTemplate().postForLocation("http://" + baseUrl + "/fuentes", requestEntity);
    }
  }
}


