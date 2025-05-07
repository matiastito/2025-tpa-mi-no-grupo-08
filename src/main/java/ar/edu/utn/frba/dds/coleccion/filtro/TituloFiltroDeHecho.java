package ar.edu.utn.frba.dds.coleccion.filtro;

import ar.edu.utn.frba.dds.hecho.Hecho;

public class TituloFiltroDeHecho implements FiltroDeHecho {

  private final String titulo;

  public TituloFiltroDeHecho(String titulo) {
    this.titulo = titulo;
  }

  @Override
  public boolean filtrar(Hecho hecho) {
    return hecho.getTitulo().equalsIgnoreCase(this.titulo);
  }
}
