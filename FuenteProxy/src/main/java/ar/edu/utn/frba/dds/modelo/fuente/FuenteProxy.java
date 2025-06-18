package ar.edu.utn.frba.dds.modelo.fuente;

import ar.edu.utn.frba.dds.web.dto.HechoDTO;

import java.util.Collection;

public interface FuenteProxy {
  Collection<HechoDTO> hechos();

  void eliminar(HechoDTO hecho);
}
