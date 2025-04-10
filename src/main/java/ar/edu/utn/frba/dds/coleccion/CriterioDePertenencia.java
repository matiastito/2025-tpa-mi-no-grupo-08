package ar.edu.utn.frba.dds.coleccion;

import ar.edu.utn.frba.dds.hecho.Hecho;

public interface CriterioDePertenencia {
  boolean pertenece(Hecho hecho);
}
