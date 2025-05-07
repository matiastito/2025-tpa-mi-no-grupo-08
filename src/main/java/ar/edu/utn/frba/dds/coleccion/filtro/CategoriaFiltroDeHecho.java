package ar.edu.utn.frba.dds.coleccion.filtro;

import ar.edu.utn.frba.dds.hecho.Categoria;
import ar.edu.utn.frba.dds.hecho.Hecho;

public class CategoriaFiltroDeHecho implements FiltroDeHecho {

  private final Categoria categoria;

  public CategoriaFiltroDeHecho(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean filtrar(Hecho hecho) {
    return hecho.getCategoria().equals(this.categoria);
  }
}
