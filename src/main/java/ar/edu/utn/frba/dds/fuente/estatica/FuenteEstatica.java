package ar.edu.utn.frba.dds.fuente.estatica;

import static java.lang.String.valueOf;
import static java.util.Set.of;

import ar.edu.utn.frba.dds.fuente.Fuente;
import ar.edu.utn.frba.dds.hecho.Hecho;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class FuenteEstatica implements Fuente {
  private ArchivoDeHechos archivoDeHechos;
  private Collection<Hecho> hechos = new HashSet<Hecho>();

  @Override
  public Collection<Hecho> traerHechos() {
    this.archivoDeHechos.getRegistros().forEach(this::crearColaboradoryUsuario);
    return hechos;
  }

  public void crearColaboradoryUsuario(List<String> registro) {
    String titulo = registro.get(0);
    String descripcion = registro.get(1);
    String categoria = registro.get(2);
    String latitud = registro.get(3);
    String longitud = registro.get(4);
    String fechaDelHecho = registro.get(5);
    hechos.add(new Hecho(titulo));
  }
}
