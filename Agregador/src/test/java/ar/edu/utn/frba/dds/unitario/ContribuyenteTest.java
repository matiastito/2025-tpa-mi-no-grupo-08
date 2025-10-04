package ar.edu.utn.frba.dds.unitario;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import static ar.edu.utn.frba.dds.util.FormateadorDeFecha.formatearFecha;

public class ContribuyenteTest {

  @Test
  public void sePuedeCrearContribuyenteIdentificadoConNombre() {
    Contribuyente contribuyente = new Contribuyente("Juan");
    assertNotNull(contribuyente);
  }

  @Test
  public void sePuedeCrearContribuyenteConNombreApellidoYEdad() {
    Contribuyente contribuyente = new Contribuyente("Ana", "García", formatearFecha("12/12/2001").toLocalDate());
    assertNotNull(contribuyente);
  }
}
