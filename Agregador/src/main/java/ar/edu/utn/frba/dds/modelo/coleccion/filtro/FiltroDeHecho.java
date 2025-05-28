package ar.edu.utn.frba.dds.modelo.coleccion.filtro;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;

public interface FiltroDeHecho {
  boolean filtrar(Hecho hecho);
}
