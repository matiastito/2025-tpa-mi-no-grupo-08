package ar.edu.utn.frba.dds.integracion;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.DINAMICA;
import static ar.edu.utn.frba.dds.modelo.hecho.Categorias.categoria;
import static ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen.MANUAL;
import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.ACEPTADA;
import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.PENDIENTE;
import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.RECHAZADA;
import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;
import static ar.edu.utn.frba.dds.util.FormateadorDeFecha.formatearFecha;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Escenario03Test {

  private Coleccion coleccionManual;

  @BeforeEach
  public void init() {
    Fuente fuente = mock(Fuente.class);
    when(fuente.getTipoFuente()).thenReturn(DINAMICA);
    coleccionManual = new Coleccion("Colección prueba", "Esto es una prueba", fuente);
    coleccionManual.agregarHecho(
        new Hecho(
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
    Administrador administrador = new Administrador("Carlos");
    Contribuyente contribuyente = new Contribuyente("Juan");

    Collection<SolicitudDeEliminacionDeHecho> solicitudDeEliminacionDeHechos = new HashSet<>();

    //Que la colección solo tenga un hecho
    assertEquals(coleccionManual.hechos().size(), 1);

    //Para ese hecho, no tengo solicitudes de eliminacion
    Hecho hecho = coleccionManual.hechos().stream().findFirst().get();
    assertEquals(solicitudDeEliminacionDeHechos.size(), 0);

    //Voy al hecho, y solicito eliminarlo, entonces tiene que haber 1 solicitud
    SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho =
        new SolicitudDeEliminacionDeHecho(contribuyente, hecho, "Es incorrecta la fecha.");
    solicitudDeEliminacionDeHechos.add(solicitudDeEliminacionDeHecho);
    assertEquals(solicitudDeEliminacionDeHechos.size(), 1);

    //Alguien rechaza la solicitud
    solicitudDeEliminacionDeHecho.rechazar(administrador);
    //Como esta rechazada, cuando piedo los hechos tiene que traer los mismo que tenía
    assertEquals(coleccionManual.hechos().size(), 1);
    //Y que haya una solicutud rechazada
    assertEquals(
        solicitudDeEliminacionDeHechos
            .stream()
            .filter(s -> s.getEstado().equals(RECHAZADA)).count(), 1);

    //Ahora creamos una nueva solicutud
    solicitudDeEliminacionDeHechos.add(
        new SolicitudDeEliminacionDeHecho(contribuyente, hecho, "Es incorrecta la fecha."));
    //Para ese hecho, hay 1 solicitud de nuevo
    assertEquals(solicitudDeEliminacionDeHechos.stream().filter(s -> s.getEstado().equals(PENDIENTE)).count(), 1);
    //Y esta ahora la aceptamos
    solicitudDeEliminacionDeHecho.aprobar(administrador);
    //Ahora, ya está aceptada
    assertEquals(solicitudDeEliminacionDeHechos.stream().filter(s -> s.getEstado().equals(ACEPTADA)).count(), 1);
    //Y como el hecho esta eliminado, ya no lo trae la coleccion
    assertEquals(coleccionManual.hechos().size(), 0);
  }
}
