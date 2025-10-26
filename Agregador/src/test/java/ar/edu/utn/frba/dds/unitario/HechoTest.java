package ar.edu.utn.frba.dds.unitario;

import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.PENDIENTE;
import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;
import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import ar.edu.utn.frba.dds.Categorias;
import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Etiqueta;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import ar.edu.utn.frba.dds.modelo.hecho.Ubicacion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HechoTest {
  private Hecho hecho;

  @BeforeEach
  public void setUp() {
    hecho = new Hecho(
        HechoOrigen.MANUAL,
        "Incendio en Córdoba",
        "Incendio forestal de gran magnitud",
        Categorias.categoria("Incendio forestal"),
        of(2025, 3, 12),
        crearUbicacion("-31.4", "-64.2"),
        LocalDateTime.now(),
        mock(Fuente.class)
    );
  }

  @Test
  public void hechoSeCreaCorrectamente() {
    assertEquals("Incendio en Córdoba", hecho.getTitulo());
    assertEquals(Categorias.categoria("Incendio forestal"), hecho.getCategoria());
  }

  @Test
  public void puedeEtiquetarse() {
    hecho.etiquetar(new Etiqueta("urgente"));
    assertTrue(hecho.getEtiquetas().contains(new Etiqueta("urgente")));
  }

  @Test
  public void puedeSolicitarseEliminacion() {
    var solicitud = new SolicitudDeEliminacionDeHecho(new Contribuyente("Juan"), hecho, "Contenido sensible por razones legales");
    assertTrue(solicitud.getEstado().equals(PENDIENTE));
  }

  @Test
  public void puedeSerMarcadoComoEliminado() {
    SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho = mock(SolicitudDeEliminacionDeHecho.class);
    hecho.eliminar(solicitudDeEliminacionDeHecho);
    assertTrue(hecho.estaEliminado());
  }

  @Test
  public void dosHechosConElMismoTituloSonIguales() {
    Categoria categoria = new Categoria("Incendio");
    LocalDate fecha = LocalDate.now();
    Ubicacion ubicacion = crearUbicacion("0.0", "0.0");

    Hecho hechoOriginal = new Hecho(
        HechoOrigen.MANUAL,
        "Incendio en Córdoba",
        "Descripción original",
        categoria,
        fecha,
        ubicacion,
        LocalDateTime.now(),
        mock(Fuente.class)
    );

    Hecho otroHecho = new Hecho(
        HechoOrigen.MANUAL,
        "Incendio en Córdoba",
        "Otro contenido",
        categoria,
        fecha,
        ubicacion,
        LocalDateTime.now(),
        mock(Fuente.class)
    );

    assertEquals(hechoOriginal, otroHecho);
  }
}