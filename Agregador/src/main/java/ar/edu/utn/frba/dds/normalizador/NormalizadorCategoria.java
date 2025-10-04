package ar.edu.utn.frba.dds.normalizador;

import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NormalizadorCategoria {

  @Autowired
  private CategoriaRepositorio categoriaRepositorio;

  public void normalizar(Hecho hecho) {
    String potencialCategoria = hecho.getCategoria().getNombre();
    Optional<Categoria> categoriaExistente = categoriaRepositorio.findByNombre(potencialCategoria);
    if (categoriaExistente.isEmpty()) {
      Optional<Categoria> categoriaPorSinonimo = categoriaRepositorio.findAll().stream().filter(
          categoria -> categoria.esSinonimo(potencialCategoria)
      ).findFirst();
      if (categoriaPorSinonimo.isEmpty()) {
        Categoria nueva = new Categoria(potencialCategoria);
        categoriaRepositorio.save(nueva);
        hecho.setCategoria(nueva);
      } else {
        hecho.setCategoria(categoriaPorSinonimo.get());
      }
    } else {
      hecho.setCategoria(categoriaExistente.get());
    }
  }
}
