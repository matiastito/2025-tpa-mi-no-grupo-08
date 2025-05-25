package ar.edu.utn.frba.dds.web.dto;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.time.LocalDateTime;
import java.util.Collection;

public class HechoDTO {
  private String titulo;
  private String descripcion;
  private String categoria;
  private ContenidoMultimediaDTO contenidoMultimedia;
  private UbicacionDTO ubicacion;
  private LocalDateTime fechaDelHecho;
  private LocalDateTime fechaDeCarga;
  private HechoOrigenDTO hechoOrigen;
  private Collection<String> etiquetas;
  private boolean eliminado = false;

  public static Hecho toHecho(HechoDTO hechoDTO) {
    return null;
  }

  public static HechoDTO toHechoDTO(Hecho hecho) {
    return null;
  }

  private class ContenidoMultimediaDTO {
    private String foo;
  }

  private class UbicacionDTO {
  }

  private class HechoOrigenDTO {
  }
}
