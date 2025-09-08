package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador, Long> {
  Administrador findByNombre(String nombre);
}
