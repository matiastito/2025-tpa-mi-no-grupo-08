package ar.edu.utn.frba.dds.servicio;

import static ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen.EXTERNO;
import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;
import static ar.edu.utn.frba.dds.util.fecha.FormateadorDeFecha.formatearFecha;
import static java.net.URI.create;
import static java.time.LocalDateTime.now;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteArchivoCSV;
import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.util.archivo.lector.LectorDeArchivo;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImportadorDeHechosDesdeArchivo {
  @Autowired
  private LectorDeArchivo lectorDeArchivoDeHechos;

  public Collection<Hecho> traerHechos(FuenteArchivoCSV fuenteArchivoCSV) {
    Collection<Hecho> hechos = new HashSet<Hecho>();
    this.lectorDeArchivoDeHechos
        .getRegistros(create("file://" + fuenteArchivoCSV.getUri()))
        .forEach(registro -> agregarHecho(hechos, registro, fuenteArchivoCSV));
    return hechos;
  }

  private void agregarHecho(Collection<Hecho> hechos, List<String> registro, FuenteArchivoCSV fuenteArchivoCSV) {
    String titulo = registro.get(0);
    String descripcion = registro.get(1);
    String categoria = registro.get(2);
    String latitud = registro.get(3);
    String longitud = registro.get(4);
    String fechaDelHecho = registro.get(5);

    Hecho hecho = new Hecho(
        fuenteArchivoCSV,
        EXTERNO,
        titulo, descripcion, new Categoria(categoria),
        formatearFecha(fechaDelHecho),
        crearUbicacion(latitud, longitud),
        now(),
        false
    );
    if (hechos.contains(hecho)) {
      hechos.remove(hecho);
    }
    hechos.add(hecho);
  }
}
