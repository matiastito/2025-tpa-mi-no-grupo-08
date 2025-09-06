package ar.edu.utn.frba.dds.modelo.fuente;

import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import java.util.List;

public interface FuenteProxy {
  void setId(long id);

  long getId();

  List<HechoDTO> hechos();
}
