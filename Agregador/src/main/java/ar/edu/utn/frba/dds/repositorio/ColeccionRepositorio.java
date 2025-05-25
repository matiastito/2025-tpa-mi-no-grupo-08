package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import java.util.Collection;

public interface ColeccionRepositorio {
  void guardar(Coleccion coleccion);

  Collection<Coleccion> colleciones();

  Coleccion collecion(String coleccionId);
}
