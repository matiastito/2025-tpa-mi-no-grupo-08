package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import java.util.Collection;

public interface ContribuyenteRepositorio {
  void guardar(Contribuyente contribuyente);

  Collection<Contribuyente> contribuyentes();

  Contribuyente contribuyente(String nombre, String apellido);

  Contribuyente contribuyente(Long contribuyenteId);
}
