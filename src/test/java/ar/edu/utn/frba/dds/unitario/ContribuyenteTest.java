package ar.edu.utn.frba.dds.unitario;

import static ar.edu.utn.frba.dds.util.DateHelper.formatearFecha;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ar.edu.utn.frba.dds.colaborador.Contribuyente;
import org.junit.jupiter.api.Test;

public class ContribuyenteTest {

  @Test
  public void sePuedeCrearContribuyenteIdentificadoConNombre() {
    Contribuyente contribuyente = Contribuyente.crearContribuyente("Juan");
    assertNotNull(contribuyente);
  }

  @Test
  public void sePuedeCrearContribuyenteConNombreApellidoYEdad() {
    Contribuyente contribuyente = Contribuyente.crearContribuyente("Ana", "García", formatearFecha("12/12/2001").toLocalDate());
    assertNotNull(contribuyente);
  }
}
