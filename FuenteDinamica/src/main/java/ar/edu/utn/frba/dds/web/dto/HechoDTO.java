package ar.edu.utn.frba.dds.web.dto;

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
}
