package ar.edu.utn.frba.dds.coleccion.criterioPertenencia;

import ar.edu.utn.frba.dds.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.hecho.Categoria;
import ar.edu.utn.frba.dds.hecho.HechoOrigen;
import ar.edu.utn.frba.dds.hecho.contenido.ContenidoMultimedia;
import ar.edu.utn.frba.dds.hecho.Hecho;
import ar.edu.utn.frba.dds.hecho.Ubicacion;

import java.time.LocalDateTime;
import java.util.Collection;

public class CriterioDePertenenciaBuilder {
  private String titulo;
  private String descripcion;
  private Categoria categoria;
  private ContenidoMultimedia contenidoMultimedia;
  private Ubicacion lugar;
  private LocalDateTime fechaDelHechoDesde;
  private LocalDateTime fechaDelHechoHasta;
  private LocalDateTime fechaDeCargaDesde;
  private LocalDateTime fechaDeCargaHasta;
  private HechoOrigen hechoOrigen;
  private Contribuyente contribuyente;
  private Collection<String> etiquetas;


  public CriterioDePertenenciaBuilder withTitulo(String titulo) {
    this.titulo = titulo;
    return this;
  }

  public CriterioDePertenenciaBuilder withDescripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  public CriterioDePertenenciaBuilder withCategoria(Categoria categoria) {
    this.categoria = categoria;
    return this;
  }

  //TODO completear con los distintos atributos.

  public CriterioDePertenencia build() {
    CriterioDePertenenciaImpl criterioDePertenenciaImpl = new CriterioDePertenenciaImpl();
    criterioDePertenenciaImpl.titulo = this.titulo;
    criterioDePertenenciaImpl.descripcion = this.descripcion;
    criterioDePertenenciaImpl.categoria = this.categoria;
    return criterioDePertenenciaImpl;
  }

  class CriterioDePertenenciaImpl implements CriterioDePertenencia {
    private String titulo;
    private String descripcion;
    private Categoria categoria;
    private ContenidoMultimedia contenidoMultimedia;
    private Ubicacion lugar;
    private LocalDateTime fechaDelHechoDesde;
    private LocalDateTime fechaDelHechoHasta;
    private LocalDateTime fechaDeCargaDesde;
    private LocalDateTime fechaDeCargaHasta;
    private HechoOrigen hechoOrigen;

    @Override
    public boolean pertenece(Hecho hecho) {
      return false;
    }
  }
}
