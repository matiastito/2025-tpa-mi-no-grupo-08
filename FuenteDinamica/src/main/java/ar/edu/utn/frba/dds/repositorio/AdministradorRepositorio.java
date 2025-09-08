package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepositorio extends JpaRepository<Administrador, Long> {
  Administrador findByNombre(String nombre);
}
