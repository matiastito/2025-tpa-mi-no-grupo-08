package ar.edu.utn.frba.dds.fuente.estatica;

import static ar.edu.utn.frba.dds.DateHelper.formatearFecha;
import static ar.edu.utn.frba.dds.hecho.HechoOrigen.EXTERNO;
import static ar.edu.utn.frba.dds.hecho.Ubicacion.crearUbicacion;
import static java.time.LocalDateTime.now;

import ar.edu.utn.frba.dds.fuente.Fuente;
import ar.edu.utn.frba.dds.hecho.Categoria;
import ar.edu.utn.frba.dds.hecho.Hecho;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class FuenteEstatica implements Fuente {
  private ArchivoDeHechos archivoDeHechos;
  private Collection<Hecho> hechos = new HashSet<Hecho>();

  private FuenteEstatica(ArchivoDeHechos archivoDeHechos) {
    this.archivoDeHechos = archivoDeHechos;
  }

  public static FuenteEstatica crear() {
    return new FuenteEstatica(
        new ArchivoDeHechosImpl("desastres_naturales_argentina"));
  }

  @Override
  public Collection<Hecho> traerHechos() {
    this.archivoDeHechos.getRegistros().forEach(this::agregarHecho);
    return hechos;
  }

  private void agregarHecho(List<String> registro) {
    String titulo = registro.get(0);
    String descripcion = registro.get(1);
    String categoria = registro.get(2);
    String latitud = registro.get(3);
    String longitud = registro.get(4);
    String fechaDelHecho = registro.get(5);

    hechos.add(Hecho.crearHechoDeTexto(
        EXTERNO,
        titulo, descripcion, new Categoria(categoria),
        formatearFecha(fechaDelHecho),
        crearUbicacion(latitud, longitud),
        now()
    ));
  }
}
