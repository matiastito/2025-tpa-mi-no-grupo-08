package ar.edu.utn.frba.dds.hecho;

import ar.edu.utn.frba.dds.hecho.contenido.ContenidoMultimedia;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Hecho {
  private String titulo;
  private String descripcion;
  private Categoria categoria;
  private ContenidoMultimedia contenidoMultimedia;
  private Ubicacion lugar;
  private LocalDateTime fechaDelHecho;
  private LocalDateTime fechaDeCarga;
  private HechoOrigen hechoOrigen;
  private Collection<String> etiquetas;
  private Collection<SolicitudDeEliminacionDeHecho> solicitudDeEliminacionDeHechosPendientes;
  private boolean eliminado = false;

  private Hecho(HechoOrigen hechoOrigen,
                String titulo,
                String descripcion,
                Categoria categoria,
                LocalDateTime fechaDelHecho,
                Ubicacion lugar,
                LocalDateTime fechaDeCarga) {
    this.hechoOrigen = hechoOrigen;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.fechaDelHecho = fechaDelHecho;
    this.lugar = lugar;
    this.fechaDeCarga = fechaDeCarga;
    this.etiquetas = new HashSet<>();
    this.solicitudDeEliminacionDeHechosPendientes = new HashSet<>();
  }

  public static Hecho crearHechoDeTexto(
      HechoOrigen hechoOrigen,
      String titulo,
      String descripcion,
      Categoria categoria,
      LocalDateTime fechaDelHecho,
      Ubicacion lugar,
      LocalDateTime fechaDeCarga) {
    return new Hecho(hechoOrigen, titulo, descripcion,
        categoria, fechaDelHecho, lugar, fechaDeCarga);
  }

  public SolicitudDeEliminacionDeHecho solicitarEliminacion(String motivo) {
    SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho =
        new SolicitudDeEliminacionDeHecho(this, motivo);
    this.solicitudDeEliminacionDeHechosPendientes.add(solicitudDeEliminacionDeHecho);
    return solicitudDeEliminacionDeHecho;
  }

  public boolean estaEliminado() {
    return this.eliminado;
  }

  public void eliminar() {
    this.eliminado = true;
  }

  public LocalDateTime getFechaDelHecho() {
    return fechaDelHecho;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public String getTitulo() {
    return titulo;
  }

  public void etiquetar(String etiqueta) {
    this.etiquetas.add(etiqueta);
  }

  public Collection<String> getEtiquetas() {
    return etiquetas;
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

  public Collection<SolicitudDeEliminacionDeHecho> getSolicitudesDeEliminacionPendientes() {
    return this.solicitudDeEliminacionDeHechosPendientes;
  }
}
