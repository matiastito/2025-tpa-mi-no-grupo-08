package ar.edu.utn.frba.dds.coleccion.filtro;

import ar.edu.utn.frba.dds.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.hecho.Categoria;
import ar.edu.utn.frba.dds.hecho.Hecho;
import ar.edu.utn.frba.dds.hecho.HechoOrigen;
import ar.edu.utn.frba.dds.hecho.Ubicacion;
import ar.edu.utn.frba.dds.hecho.contenido.TipoContenidoMultimedia;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.function.BiFunction;

public class FiltroDeHechoBuilder {
  private String titulo;
  private String descripcion;
  private Categoria categoria;
  private TipoContenidoMultimedia tipoContenidoMultimedia;
  private Ubicacion ubicacion;
  private LocalDateTime fechaDelHechoDesde;
  private LocalDateTime fechaDelHechoHasta;
  private LocalDateTime fechaDeCargaDesde;
  private LocalDateTime fechaDeCargaHasta;
  private HechoOrigen hechoOrigen;
  private Contribuyente contribuyente;
  private Collection<String> etiquetas;

  public FiltroDeHechoBuilder conTitulo(String titulo) {
    this.titulo = titulo;
    return this;
  }

  public FiltroDeHechoBuilder conDescripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  public FiltroDeHechoBuilder conCategoria(Categoria categoria) {
    this.categoria = categoria;
    return this;
  }

  public FiltroDeHechoBuilder conTipoContenidoMultimedia(TipoContenidoMultimedia tipoContenidoMultimedia) {
    this.tipoContenidoMultimedia = tipoContenidoMultimedia;
    return this;
  }

  public FiltroDeHechoBuilder conUbicacion(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
    return this;
  }

  public FiltroDeHechoBuilder conFechaDelHechoDesde(LocalDateTime fechaDelHechoDesde) {
    this.fechaDelHechoDesde = fechaDelHechoDesde;
    return this;
  }

  public FiltroDeHechoBuilder conFechaDelHechoHasta(LocalDateTime fechaDelHechoHasta) {
    this.fechaDelHechoHasta = fechaDelHechoHasta;
    return this;
  }

  public FiltroDeHechoBuilder conFechaDeCargaDesde(LocalDateTime fechaDeCargaDesde) {
    this.fechaDeCargaDesde = fechaDeCargaDesde;
    return this;
  }

  public FiltroDeHechoBuilder conFechaDeCargaHasta(LocalDateTime fechaDeCargaHasta) {
    this.fechaDeCargaHasta = fechaDeCargaHasta;
    return this;
  }

  public FiltroDeHechoBuilder conHechoOrigen(HechoOrigen hechoOrigen) {
    this.hechoOrigen = hechoOrigen;
    return this;
  }

  public FiltroDeHechoBuilder conContribuyente(Contribuyente contribuyente) {
    this.contribuyente = contribuyente;
    return this;
  }

  public FiltroDeHechoBuilder conEtiquetas(Collection<String> etiquetas) {
    this.etiquetas = etiquetas;
    return this;
  }

  public FiltroDeHecho build() {
    FiltroDeHechoImpl filtroDeHechoImpl = new FiltroDeHechoImpl();
    filtroDeHechoImpl.titulo = this.titulo;
    filtroDeHechoImpl.descripcion = this.descripcion;
    filtroDeHechoImpl.categoria = this.categoria;
    filtroDeHechoImpl.tipoContenidoMultimedia = this.tipoContenidoMultimedia;
    filtroDeHechoImpl.ubicacion = this.ubicacion;
    filtroDeHechoImpl.fechaDelHechoDesde = fechaDelHechoDesde;
    filtroDeHechoImpl.fechaDelHechoHasta = fechaDelHechoHasta;
    filtroDeHechoImpl.fechaDeCargaDesde = fechaDeCargaDesde;
    filtroDeHechoImpl.fechaDeCargaHasta = fechaDeCargaHasta;
    filtroDeHechoImpl.hechoOrigen = hechoOrigen;
    filtroDeHechoImpl.etiquetas = etiquetas;
    return filtroDeHechoImpl;
  }

  private class FiltroDeHechoImpl implements FiltroDeHecho {
    private String titulo;
    private String descripcion;
    private Categoria categoria;
    private TipoContenidoMultimedia tipoContenidoMultimedia;
    private Ubicacion ubicacion;
    private LocalDateTime fechaDelHechoDesde;
    private LocalDateTime fechaDelHechoHasta;
    private LocalDateTime fechaDeCargaDesde;
    private LocalDateTime fechaDeCargaHasta;
    private HechoOrigen hechoOrigen;
    private Collection<String> etiquetas;

    @Override
    public boolean filtrar(Hecho hecho) {
      boolean ret = true;
      ret &= aplicarFiltro(this.fechaDelHechoDesde, hecho.getFechaDelHecho(), LocalDateTime::isAfter);
      ret &= aplicarFiltro(this.fechaDelHechoHasta, hecho.getFechaDelHecho(), LocalDateTime::isBefore);
      ret &= aplicarFiltro(this.categoria, hecho.getCategoria(), Categoria::equals);
      ret &= aplicarFiltro(this.titulo, hecho.getTitulo(), String::equals);
      return ret;
    }

    private <P, S> boolean aplicarFiltro(P valorDelFiltro, S valorDelHecho, BiFunction<S, P, Boolean> funcionAAplicar) {
      return valorDelFiltro != null ? funcionAAplicar.apply(valorDelHecho, valorDelFiltro) : true;
    }
  }
}
