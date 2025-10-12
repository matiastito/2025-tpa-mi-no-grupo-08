package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.model.dto.HechoDTO;
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
}
