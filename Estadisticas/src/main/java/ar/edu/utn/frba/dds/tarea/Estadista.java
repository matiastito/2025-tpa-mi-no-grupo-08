package ar.edu.utn.frba.dds.tarea;

import static java.time.LocalDate.now;
import ar.edu.utn.frba.dds.model.Categoria;
import ar.edu.utn.frba.dds.model.Fuente;
import ar.edu.utn.frba.dds.model.Provincia;
import ar.edu.utn.frba.dds.model.estadistica.Estadistica;
import ar.edu.utn.frba.dds.repositorio.CategoriaRepositorio;
import ar.edu.utn.frba.dds.repositorio.EstadisticaRepositorio;
import ar.edu.utn.frba.dds.repositorio.FuenteRepositorio;
import ar.edu.utn.frba.dds.repositorio.ProvinciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
GET /colecciones
GET /colecciones/:id/hechos
#Hechos porProvincia porColeccion
     De una colección, ¿en qué provincia se agrupan la mayor cantidad de hechos reportados?
GET /Hechos
#Hechos porCategoria              ¿Cuál es la categoría con mayor cantidad de hechos reportados?
#Hechos porProvincia porCategoria ¿En qué provincia se presenta la mayor cantidad de hechos de una cierta categoría?
#Hechos porHora porCategoria      ¿A qué hora del día ocurren la mayor cantidad de hechos de una cierta categoría?
GET /solicitudes                  ¿Cuántas solicitudes de eliminación son spam?
*/
@Component
public class Estadista {
  @Autowired
  private EstadisticaRepositorio estadisticaRepositorio;

  @Autowired
  private FuenteRepositorio fuenteRepositorio;

  @Autowired
  private CategoriaRepositorio categoriaRepositorio;

  @Autowired
  private ProvinciaRepositorio provinciaRepositorio;

  @Scheduled(fixedRate = 500)
  public void generarEstadisticas() {
    Estadistica estadistica = new Estadistica(now());

    List<Fuente> fuentes = fuenteRepositorio.findAll();
    fuentes.forEach(f -> {
      // #Hechos porProvincia porColeccion
      f.colecciones().forEach(c -> {
        f.coleccion(c.getHandle()).forEach(h -> {
          Provincia provincia =
              provinciaRepositorio.findByNombre(h.getUbicacion().getProvincia())
                  .orElse(provinciaRepositorio.save(new Provincia(h.getUbicacion().getProvincia())));
          estadistica.registrarHecho(provincia, c.getHandle());
        });
      });

      // #Hechos porCategoria
      // #Hechos porProvincia porCategoria
      // #Hechos porHora porCategoria
      f.hechos().forEach(h -> {
        Categoria categoria =
            categoriaRepositorio.findByNombre(h.getCategoria())
                .orElse(categoriaRepositorio.save(new Categoria(h.getCategoria())));
        Provincia provincia =
            provinciaRepositorio.findByNombre(h.getUbicacion().getProvincia())
                .orElse(provinciaRepositorio.save(new Provincia(h.getUbicacion().getProvincia())));
        int horaDelHecho = h.getFechaDelHecho().getHour();
        estadistica.registarHecho(categoria, provincia, horaDelHecho);
      });

      // #SolicitudesSpam
      int cantidadDeSolicutudesSpam = f.solicitudes().stream().filter(
          seh -> seh.
              getSolicitudDeEliminacionDeHechoEstado()
              .equalsIgnoreCase("RECHAZADA_POR_SPAM")
      ).collect(Collectors.toSet()).size();
      estadistica.registrarCantidadDeSolicitudesRechazadasPorSpam(cantidadDeSolicutudesSpam);
    });

    estadisticaRepositorio.save(estadistica);
  }

}
