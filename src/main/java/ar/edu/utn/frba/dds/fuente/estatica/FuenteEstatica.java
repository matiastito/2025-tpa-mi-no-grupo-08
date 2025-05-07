package ar.edu.utn.frba.dds.fuente.estatica;

import static ar.edu.utn.frba.dds.hecho.HechoOrigen.EXTERNO;
import static ar.edu.utn.frba.dds.hecho.Ubicacion.crearUbicacion;
import static ar.edu.utn.frba.dds.util.DateHelper.formatearFecha;
import static java.time.LocalDateTime.now;

import ar.edu.utn.frba.dds.fuente.estatica.archivo.lector.LectorDeArchivo;
import ar.edu.utn.frba.dds.fuente.estatica.archivo.localizador.LocalizadorDeArchivo;
import ar.edu.utn.frba.dds.hecho.Categoria;
import ar.edu.utn.frba.dds.hecho.Hecho;
import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class FuenteEstatica {
  private LectorDeArchivo lectorDeArchivoDeHechos;
  private LocalizadorDeArchivo localizadorDeArchivo;

  public FuenteEstatica(LectorDeArchivo lectorDeArchivoDeHechos, LocalizadorDeArchivo localizadorDeArchivo) {
    this.lectorDeArchivoDeHechos = lectorDeArchivoDeHechos;
    this.localizadorDeArchivo = localizadorDeArchivo;
  }

  public Collection<Hecho> traerHechos() {
    Collection<Hecho> hechos = new HashSet<Hecho>();
    URI archivoURI = localizadorDeArchivo.getURI();
    this.lectorDeArchivoDeHechos.getRegistros(archivoURI).forEach(
        r -> agregarHecho(hechos, r));
    return hechos;
  }

  private void agregarHecho(Collection<Hecho> hechos, List<String> registro) {
    String titulo = registro.get(0);
    String descripcion = registro.get(1);
    String categoria = registro.get(2);
    String latitud = registro.get(3);
    String longitud = registro.get(4);
    String fechaDelHecho = registro.get(5);

    Hecho hecho = new Hecho(
        EXTERNO,
        titulo, descripcion, new Categoria(categoria),
        formatearFecha(fechaDelHecho),
        crearUbicacion(latitud, longitud),
        now()
    );
    if (hechos.contains(hecho)) {
      hechos.remove(hecho);
    }
    hechos.add(hecho);
  }
}
