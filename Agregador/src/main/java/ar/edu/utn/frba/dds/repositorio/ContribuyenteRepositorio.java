package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContribuyenteRepositorio extends JpaRepository<Contribuyente, Long> {
  Optional<Contribuyente> findByNombreAndApellido(String nombre, String Apellido);
}
