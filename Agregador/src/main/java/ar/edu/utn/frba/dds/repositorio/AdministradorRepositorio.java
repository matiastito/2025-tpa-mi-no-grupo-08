package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador, Long> {
  Optional<Administrador> findByNombre(String nombre);
}
