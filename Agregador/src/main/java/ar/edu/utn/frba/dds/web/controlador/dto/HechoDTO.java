package ar.edu.utn.frba.dds.web.controlador.dto;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;

public class HechoDTO {
  private final HechoOrigen hechoOrigen;
  private final String fechaDelHecho;

  private HechoDTO(HechoOrigen hechoOrigen, String fechaDelHecho) {
    this.hechoOrigen = hechoOrigen;
    this.fechaDelHecho = fechaDelHecho;
  }

  public HechoOrigen getHechoOrigen() {
    return hechoOrigen;
  }

  public String getFechaDelHecho() {
    return fechaDelHecho;
  }

  public static HechoDTO toDTO(Hecho hecho) {
    return new HechoDTO(hecho.getHechoOrigen(), hecho.getFechaDelHecho().toString());
  }

  public static Hecho toModel(HechoDTO hechoDTO) {
    return null;
  }
}
