package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
  List<Categoria> findByNombre(String nombre);
}
