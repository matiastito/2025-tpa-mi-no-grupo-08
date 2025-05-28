package ar.edu.utn.frba.dds.modelo.hecho;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

//TODO Mover a Test
public class Categorias {
  private static Collection<Categoria> categorias = new HashSet<>();

  public static Categoria categoria(String nombre) {
    Optional<Categoria> categoriaBuscada = categorias.stream().filter(categoria -> categoria.getNombre().equals(nombre)).findFirst();
    if (categoriaBuscada.isPresent()) {
      return categoriaBuscada.get();
    } else {
      Categoria categoriaNuueva = new Categoria(nombre);
      categorias.add(categoriaNuueva);
      return categoriaNuueva;
    }
  }
}
