package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.model.dto.ColeccionDTO.FuenteDTO;
import ar.edu.utn.frba.dds.model.dto.HechoDTO;
import ar.edu.utn.frba.dds.model.dto.HechoModificacionDTO;
import ar.edu.utn.frba.dds.model.dto.SolicitudDeEliminacionDeHechoDTO;
import ar.edu.utn.frba.dds.model.dto.TipoConsenso;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AgregadorServicio {
  private final WebAPICallerService webApiCallerService;
  private final String agregadorServiceUrl;
  private final RestTemplate restTemplate;

  @Autowired
  public AgregadorServicio(WebAPICallerService webApiCallerService,
                           @Value("${agregador.service.url}") String agregadorServiceUrl) {
    this.webApiCallerService = webApiCallerService;
    this.agregadorServiceUrl = agregadorServiceUrl;
    this.restTemplate = new RestTemplate();
  }

  public List<HechoDTO> hechos() {
    return webApiCallerService.getList(agregadorServiceUrl + "/hechos", HechoDTO.class);
  }

  public List<HechoDTO> hechos(Long coleccionHandleId) {
    return webApiCallerService.getList(agregadorServiceUrl + "/colecciones/" + coleccionHandleId + "/hechos", HechoDTO.class);
  }

  public List<ColeccionDTO> colecciones() {
    return webApiCallerService.getList(agregadorServiceUrl + "/colecciones", ColeccionDTO.class);
  }

  public void crearColeccion(ColeccionDTO coleccionDTO) {
    webApiCallerService.post(agregadorServiceUrl + "/colecciones", coleccionDTO, Void.class);
  }

  public ColeccionDTO coleccion(Long coleccionId) {
    return webApiCallerService.get(agregadorServiceUrl + "/colecciones/" + coleccionId, ColeccionDTO.class);
  }

  public void eliminar(Long coleccionId) {
    webApiCallerService.delete(agregadorServiceUrl + "/colecciones/" + coleccionId);
  }

  public void editarColeccion(ColeccionDTO coleccionDTO) {
    webApiCallerService.put(agregadorServiceUrl + "/colecciones", coleccionDTO, Void.class);
  }

  public void cambiarConsenso(Long coleccionId, TipoConsenso tipoConsenso) {
    webApiCallerService.put(agregadorServiceUrl + "/colecciones/" + coleccionId + "/consenso/" + tipoConsenso, Void.class);
  }

  public List<FuenteDTO> fuentes() {
    return webApiCallerService.getList(agregadorServiceUrl + "/fuentes", FuenteDTO.class);
  }

  public void editarFuente(FuenteDTO fuenteDTO) {
    webApiCallerService.put(agregadorServiceUrl + "/fuentes", fuenteDTO, Void.class);
  }

  public void crearHecho(HechoDTO hechoDTO) {
    webApiCallerService
        .post(agregadorServiceUrl + "/hechos", hechoDTO, Void.class);
  }

  public void crearModificacionHecho(HechoModificacionDTO hechoModificacionDTO) {
    webApiCallerService
        .post(agregadorServiceUrl + "/hechosModificaciones", hechoModificacionDTO, Void.class);
  }

  public void importarHechos(MultipartFile file) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    MultiValueMap<String, Object> body
        = new LinkedMultiValueMap<>();
    body.add("archivoCSVDeHechos", file.getResource());
    HttpEntity<MultiValueMap<String, Object>> requestEntity
        = new HttpEntity<>(body, headers);
    restTemplate.postForLocation(agregadorServiceUrl + "/hechos/archivo", requestEntity);
  }

  public List<HechoModificacionDTO> hechosModificaciones() {
    return webApiCallerService.getList(agregadorServiceUrl + "/hechosModificaciones", HechoModificacionDTO.class);
  }

  public void editarModificacionHecho(HechoModificacionDTO hechoModificacionDTO) {
    webApiCallerService
        .put(agregadorServiceUrl + "/hechosModificaciones", hechoModificacionDTO, Void.class);
  }

  public void solicitarEliminacionDeHecho(SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO) {
    webApiCallerService
        .post(agregadorServiceUrl + "/solicitudes", solicitudDeEliminacionDeHechoDTO, Void.class);
  }

  public List<SolicitudDeEliminacionDeHechoDTO> solicitudesEliminacion() {
    return webApiCallerService.getList(agregadorServiceUrl + "/solicitudes", SolicitudDeEliminacionDeHechoDTO.class);
  }

  public void editarSolicitudEliminacion(SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO) {
    webApiCallerService
        .put(agregadorServiceUrl + "/solicitudes", solicitudDeEliminacionDeHechoDTO, Void.class);

  }
}
