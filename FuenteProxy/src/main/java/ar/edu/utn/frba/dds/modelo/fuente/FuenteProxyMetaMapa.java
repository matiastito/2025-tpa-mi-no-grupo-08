package ar.edu.utn.frba.dds.modelo.fuente;

import ar.edu.utn.frba.dds.web.dto.HechoDTO;

public interface FuenteProxyMetaMapa extends FuenteProxy {
  void eliminar(HechoDTO hecho);
}
