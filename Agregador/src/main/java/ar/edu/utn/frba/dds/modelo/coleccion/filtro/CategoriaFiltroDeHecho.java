package ar.edu.utn.frba.dds.modelo.coleccion.filtro;

import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;

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
