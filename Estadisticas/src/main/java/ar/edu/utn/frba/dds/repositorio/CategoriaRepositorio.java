package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
  Optional<Categoria> findByNombre(String nombre);
}
