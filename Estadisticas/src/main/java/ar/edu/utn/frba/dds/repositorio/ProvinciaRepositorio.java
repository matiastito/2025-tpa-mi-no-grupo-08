package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepositorio extends JpaRepository<Provincia, Long> {
  Provincia findByNombre(String nombre);
}
