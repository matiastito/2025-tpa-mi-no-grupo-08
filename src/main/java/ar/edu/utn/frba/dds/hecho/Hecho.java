package ar.edu.utn.frba.dds.hecho;

import ar.edu.utn.frba.dds.hecho.origen.HechoOrigen;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Hecho {
  private String titulo;
  private String descripcion;
  private Categoria categoria;
  private ContenidoMultimedia contenidoMultimedia;
  private Ubicacion lugar;
  private LocalDateTime fechaDelHecho;
  private LocalDateTime fechaDeCarga;

  /*
  FIXME?
   Aca hay que ver el tema de los visualizadores VS contribuyentes, yo lo dejaría así:
    - Si en un contribuyente, puede tener etiquetas.
    - Si es un visualizador, no se puede etiquetar
  */
  private HechoOrigen hechoOrigen;

  private boolean eliminado = false;

  public Hecho(String titulo) {
    this.titulo = titulo;
  }

  public void eliminar() {
    this.eliminado = true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Hecho hecho)) return false;
    return Objects.equals(titulo, hecho.titulo);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(titulo);
  }

  public boolean isEliminado() {
    return this.eliminado;
  }
}
