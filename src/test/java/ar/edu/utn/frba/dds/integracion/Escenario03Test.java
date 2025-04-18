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
  public void criteriosDePertenencia() {
    assertEquals(coleccionManual.hechos().size(), 1);
    Hecho hecho = coleccionManual.hechos().stream().findFirst().get();
    assertEquals(hecho.getSolicitudesDeEliminacionPendientes().size(), 0);
    SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho =
        hecho.solicitarEliminacion("Es incorrecta la fecha.");
    assertEquals(hecho.getSolicitudesDeEliminacionPendientes().size(), 1);
    solicitudDeEliminacionDeHecho.rechazar();
    assertEquals(coleccionManual.hechos().size(), 1);
    assertEquals(hecho.getSolicitudesDeEliminacionPendientes().size(), 0);
    solicitudDeEliminacionDeHecho = hecho.solicitarEliminacion("Es incorrecta la fecha.");
    assertEquals(hecho.getSolicitudesDeEliminacionPendientes().size(), 1);
    solicitudDeEliminacionDeHecho.aceptar();
    assertEquals(hecho.getSolicitudesDeEliminacionPendientes().size(), 0);
    assertEquals(coleccionManual.hechos().size(), 0);
  }
}
