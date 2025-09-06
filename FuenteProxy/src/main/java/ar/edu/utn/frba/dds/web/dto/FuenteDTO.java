package ar.edu.utn.frba.dds.web.dto;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.PROXY_DDS;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxyAPIdeDDS;
import ar.edu.utn.frba.dds.modelo.fuente.TipoFuente;

public class FuenteDTO {
  private TipoFuente tipoFuente;
  private String baseUrl;

  public TipoFuente getTipoFuente() {
    return tipoFuente;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setTipoFuente(TipoFuente tipoFuente) {
    this.tipoFuente = tipoFuente;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public static FuenteProxyAPIdeDDS toModelAPIdeDDS(FuenteDTO fuenteDTO) {
    if (PROXY_DDS.equals(fuenteDTO.getTipoFuente())) {
      return new FuenteProxyAPIdeDDS(fuenteDTO.getBaseUrl());
    }
    throw new RuntimeException("Tipo de Fuente invalida.");
  }
}
