package ar.edu.utn.frba.dds.unitario;

import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.PENDIENTE;
import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;
import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.modelo.hecho.Categorias;
import ar.edu.utn.frba.dds.modelo.hecho.Etiqueta;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
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
        of(2025, 3, 12, 10, 0),
        crearUbicacion("-31.4", "-64.2"),
        now()
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
    hecho.eliminar();
    assertTrue(hecho.estaEliminado());
  }

  @Test
  public void dosHechosConElMismoTituloSonIguales() {
    Hecho otroHecho = new Hecho(
        HechoOrigen.MANUAL,
        "Incendio en Córdoba",
        "Otro contenido",
        Categorias.categoria("Otro"),
        now(),
        crearUbicacion("0.0", "0.0"),
        now()
    );
    assertEquals(hecho, otroHecho);
  }
}
