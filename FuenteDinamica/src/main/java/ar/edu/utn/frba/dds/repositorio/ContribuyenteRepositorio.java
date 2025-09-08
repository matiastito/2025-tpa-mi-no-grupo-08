package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContribuyenteRepositorio extends JpaRepository<Contribuyente, Long> {
  Contribuyente findByNombreAndApellido(String nombre, String apellido);
}
