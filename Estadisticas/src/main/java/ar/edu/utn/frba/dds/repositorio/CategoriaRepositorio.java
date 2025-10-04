package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.model.Categoria;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
  Optional<Categoria> findByNombre(String nombre);
}
