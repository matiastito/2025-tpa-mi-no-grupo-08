package ar.edu.utn.frba.dds.integracion;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.DINAMICA;
import static ar.edu.utn.frba.dds.modelo.hecho.Categorias.categoria;
import static ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen.MANUAL;
import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;
import static ar.edu.utn.frba.dds.util.FormateadorDeFecha.formatearFecha;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.CategoriaFiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.FechaDelHechoFiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.TituloFiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Etiqueta;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Escenario01Test {

  private Coleccion coleccionManual;

  @BeforeEach
  public void init() {
    Fuente fuente = mock(Fuente.class);
    when(fuente.getTipoFuente()).thenReturn(DINAMICA);
    coleccionManual = new Coleccion("Colección prueba", "Esto es una prueba", fuente);
    coleccionManual.agregarHecho(
        new Hecho(
            MANUAL,
            "Caída de aeronave impacta en Olavarría",
            "Grave caída de aeronave ocurrió en las inmediaciones de Olavarría, Buenos Aires. El incidente provocó pánico entre los residentes locales. Voluntarios de diversas organizaciones se han sumado a las tareas de auxilio.",
            categoria("Caída de aeronave"),
            formatearFecha("29/11/2001"),
            crearUbicacion("-36.868375", "-60.343297"),
            now()
        ));
    coleccionManual.agregarHecho(
        new Hecho(
            MANUAL,
            "Serio incidente: Accidente con maquinaria industrial en Chos Malal, Neuquén",
            "Un grave accidente con maquinaria industrial se registró en Chos Malal, Neuquén. El incidente dejó a varios sectores sin comunicación. Voluntarios de diversas organizaciones se han sumado a las tareas de auxilio.",
            categoria("Accidente con maquinaria industrial"),
            formatearFecha("16/08/2001"),
            crearUbicacion("-37.345571", "-70.241485"),
            now()
        ));

    coleccionManual.agregarHecho(
        new Hecho(
            MANUAL,
            "Caída de aeronave impacta en Venado Tuerto, Santa Fe",
            "Grave caída de aeronave ocurrió en las inmediaciones de Venado Tuerto, Santa Fe. El incidente destruyó viviendas y dejó a familias evacuadas. Autoridades nacionales se han puesto a disposición para brindar asistencia.",
            categoria("Caída de aeronave"),
            formatearFecha("08/08/2008"),
            crearUbicacion("-33.768051", "-61.921032"),
            now()
        ));

    coleccionManual.agregarHecho(
        new Hecho(
            MANUAL,
            "Accidente en paso a nivel deja múltiples daños en Pehuajó, Buenos Aires",
            "Grave accidente en paso a nivel ocurrió en las inmediaciones de Pehuajó, Buenos Aires. El incidente generó preocupación entre las autoridades provinciales. El Ministerio de Desarrollo Social está brindando apoyo a los damnificados.",
            categoria("Accidente en paso a nivel"),
            formatearFecha("27/01/2020"),
            crearUbicacion("-35.855811", "-61.940589"),
            now()
        ));

    coleccionManual.agregarHecho(
        new Hecho(
            MANUAL,
            "Devastador Derrumbe en obra en construcción afecta a Presidencia Roque Sáenz Peña",
            "Un grave derrumbe en obra en construcción se registró en Presidencia Roque Sáenz Peña, Chaco. El incidente generó preocupación entre las autoridades provinciales. El intendente local se ha trasladado al lugar para supervisar las operaciones.",
            categoria("Derrumbe en obra en construcción"),
            formatearFecha("04/06/2016"),
            crearUbicacion("-26.780008", "-60.458782"),
            now()
        ));
  }

  @Test
  public void criteriosDePertenencia() {
    FiltroDeHecho filtroFecha =
        new FechaDelHechoFiltroDeHecho(formatearFecha("01/01/2000"), formatearFecha("01/01/2010"));

    coleccionManual.agregarFiltro(filtroFecha);

    assertEquals(coleccionManual.hechos().size(), 3);

    filtroFecha = new CategoriaFiltroDeHecho(categoria("Caída de aeronave"));

    coleccionManual.agregarFiltro(filtroFecha);

    assertEquals(coleccionManual.hechos().size(), 2);
  }

  @Test
  public void filtrosDelVisualizador() {
    FiltroDeHecho filtro =
        new CategoriaFiltroDeHecho(categoria("Caída de aeronave"));

    coleccionManual.agregarFiltro(filtro);

    filtro = new TituloFiltroDeHecho("un título");

    coleccionManual.agregarFiltro(filtro);

    assertEquals(coleccionManual.hechos().size(), 0);
  }

  @Test
  public void etiquetas() {
    FiltroDeHecho filtro = new TituloFiltroDeHecho("Caída de aeronave impacta en Olavarría");

    coleccionManual.agregarFiltro(filtro);

    assertEquals(coleccionManual.hechos().size(), 1);

    Hecho hecho = coleccionManual.hechos().stream().findFirst().get();
    hecho.etiquetar(new Etiqueta("Olavarria"));
    hecho.etiquetar(new Etiqueta("Grave"));

    assertEquals(hecho.getEtiquetas().size(), 2);
  }
}
