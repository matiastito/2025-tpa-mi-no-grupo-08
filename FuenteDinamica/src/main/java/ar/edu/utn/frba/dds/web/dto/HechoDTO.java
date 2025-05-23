package ar.edu.utn.frba.dds.web.dto;

import static ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen.CONTRIBUYENTE;
import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;

import java.time.LocalDateTime;
import java.util.Collection;

public class HechoDTO {
  private String titulo;
  private String descripcion;
  private String categoria;
  //private ContenidoMultimedia contenidoMultimedia;
  //private Ubicacion ubicacion;
  private LocalDateTime fechaDelHecho;
  private LocalDateTime fechaDeCarga;
  //private HechoOrigen hechoOrigen;
  private Collection<String> etiquetas;
  private boolean eliminado = false;

  public static Hecho toHecho(HechoDTO hechoDTO) {
    return new Hecho(
        CONTRIBUYENTE, hechoDTO.titulo, hechoDTO.descripcion,
        new Categoria(hechoDTO.categoria), null, null, null);
  }

  public static HechoDTO toDTO(Hecho hecho) {
    return new HechoDTO();
  }

}
