package ar.edu.utn.frba.dds.coleccion;

import ar.edu.utn.frba.dds.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.hecho.Hecho;
import java.util.ArrayList;
import java.util.List;

public class FiltrosParaHecho {
  private List<FiltroDeHecho> filtros;

  public FiltrosParaHecho() {
    this.filtros = new ArrayList<>();
  }

  public boolean aplicarFiltros(Hecho hecho) {
    return this.filtros.stream().allMatch(
        filtroDeHecho -> filtroDeHecho.filtrar(hecho));
  }

  public void agregarFiltro(FiltroDeHecho filtro) {
    this.filtros.add(filtro);
  }
}
