package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.model.dto.ColeccionDTO.FuenteDTO;
import ar.edu.utn.frba.dds.model.dto.HechoDTO;
import ar.edu.utn.frba.dds.model.dto.TipoConsenso;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AgregadorServicio {
  private final WebAPICallerService webApiCallerService;
  private final String agregadorServiceUrl;

  @Autowired
  public AgregadorServicio(WebAPICallerService webApiCallerService,
                           @Value("${agregador.service.url}") String agregadorServiceUrl) {
    this.webApiCallerService = webApiCallerService;
    this.agregadorServiceUrl = agregadorServiceUrl;

  }

  public List<HechoDTO> hechos() {
    List<HechoDTO> response = webApiCallerService.getList(agregadorServiceUrl + "/hechos", HechoDTO.class);
    return response;
  }

  public List<HechoDTO> hechos(String coleccionHandleId) {
    List<HechoDTO> response = webApiCallerService.getList(agregadorServiceUrl + "/colecciones/" + coleccionHandleId + "/hechos", HechoDTO.class);
    return response;
  }

  public List<ColeccionDTO> colecciones() {
    List<ColeccionDTO> response = webApiCallerService.getList(agregadorServiceUrl + "/colecciones", ColeccionDTO.class);
    return response;
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
}
