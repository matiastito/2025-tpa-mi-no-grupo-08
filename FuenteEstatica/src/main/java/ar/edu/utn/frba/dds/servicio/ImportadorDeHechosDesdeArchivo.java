package ar.edu.utn.frba.dds.servicio;

import static ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen.EXTERNO;
import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;
import static ar.edu.utn.frba.dds.util.fecha.FormateadorDeFecha.formatearFecha;
import static java.net.URI.create;
import static java.time.LocalDateTime.now;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteArchivoCSV;
import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.RepositorioCategoria;
import ar.edu.utn.frba.dds.util.archivo.lector.LectorDeArchivo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImportadorDeHechosDesdeArchivo {
  @Autowired
  private LectorDeArchivo lectorDeArchivoDeHechos;

  @Autowired
  private RepositorioCategoria repositorioCategoria;

  public void importarHechos(FuenteArchivoCSV fuenteArchivoCSV) {
    this.lectorDeArchivoDeHechos
        .getRegistros(create("file://" + fuenteArchivoCSV.getUri()))
        .forEach(registro -> agregarHecho(registro, fuenteArchivoCSV));
  }

  private void agregarHecho(List<String> registro, FuenteArchivoCSV fuenteArchivoCSV) {
    String titulo = registro.get(0);
    String descripcion = registro.get(1);
    String nombreCategoria = registro.get(2);
    String latitud = registro.get(3);
    String longitud = registro.get(4);
    String fechaDelHecho = registro.get(5);
    Categoria categoria;
    Optional<Categoria> categoriaOptional = repositorioCategoria.findByNombre(nombreCategoria);
    if (categoriaOptional.isPresent()) {
      categoria = categoriaOptional.get();
    } else {
      categoria = new Categoria(nombreCategoria);
      repositorioCategoria.save(categoria);
    }
    Hecho hecho = new Hecho(EXTERNO, titulo, descripcion, categoria,
        formatearFecha(fechaDelHecho), crearUbicacion(latitud, longitud), now());
    fuenteArchivoCSV.agregarHecho(hecho);
  }
}
