package ar.edu.utn.frba.dds.integracion;

import static ar.edu.utn.frba.dds.DateHelper.formatearFecha;
import static ar.edu.utn.frba.dds.coleccion.Coleccion.crearColeccionManual;
import static ar.edu.utn.frba.dds.hecho.Categorias.categoria;
import static ar.edu.utn.frba.dds.hecho.Hecho.crearHechoDeTexto;
import static ar.edu.utn.frba.dds.hecho.HechoOrigen.MANUAL;
import static ar.edu.utn.frba.dds.hecho.Ubicacion.crearUbicacion;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frba.dds.coleccion.Coleccion;
import ar.edu.utn.frba.dds.hecho.Hecho;
import ar.edu.utn.frba.dds.hecho.SolicitudDeEliminacionDeHecho;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Escenario03Test {

  private Coleccion coleccionManual;

  @BeforeEach
  public void init() {
    coleccionManual = crearColeccionManual("Colección prueba", "Esto es una prueba");
    coleccionManual.agregarHecho(
        crearHechoDeTexto(
            MANUAL,
            "Brote de enfermedad contagiosa causa estragos en San Lorenzo, Santa Fe",
            "Grave brote de enfermedad contagiosa ocurrió en las inmediaciones de San Lorenzo, Santa Fe. El incidente dejó varios heridos y daños materiales. Se ha declarado estado de emergencia en la región para facilitar la asistencia.",
            categoria("Evento sanitario"),
            formatearFecha("05/07/2005"),
            crearUbicacion("-32.786098", "-60.741543"),
            now()
        ));
  }

  @Test
  public void solicitudesDeEliminacion() {
    //Que la colección solo tenga un hecho
    assertEquals(coleccionManual.hechos().size(), 1);

    //Para ese hecho, no tengo solicitudes de eliminacion
    Hecho hecho = coleccionManual.hechos().stream().findFirst().get();
    assertEquals(hecho.getSolicitudesDeEliminacionPendientes().size(), 0);

    //Voy al hecho, y solicito eliminarlo, entonces tiene que haber 1 solicitud
    SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho =
        hecho.solicitarEliminacion("Es incorrecta la fecha.");
    assertEquals(hecho.getSolicitudesDeEliminacionPendientes().size(), 1);

    //Alguien rechaza la solicitud
    solicitudDeEliminacionDeHecho.rechazar();
    //Como esta rechazada, cuando piedo los hechos tiene que traer los mismo que tenía
    assertEquals(coleccionManual.hechos().size(), 1);
    //Y que se haya eliminado la solicitud
    assertEquals(hecho.getSolicitudesDeEliminacionPendientes().size(), 0);

    //Ahora creamos una nueva solicutud
    solicitudDeEliminacionDeHecho = hecho.solicitarEliminacion("Es incorrecta la fecha.");
    //Para ese hecho, hay 1 solicitud de nuevo
    assertEquals(hecho.getSolicitudesDeEliminacionPendientes().size(), 1);
    //Y esta ahora la aceptamos
    solicitudDeEliminacionDeHecho.aceptar();
    //Ahora, ya no esta mas pendiente
    assertEquals(hecho.getSolicitudesDeEliminacionPendientes().size(), 0);
    //Y como el hecho esta eliminado, ya no lo trae la coleccion
    assertEquals(coleccionManual.hechos().size(), 0);
  }
}
