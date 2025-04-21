package ar.edu.utn.frba.dds.unitario;

import ar.edu.utn.frba.dds.colaborador.Contribuyente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContribuyenteTest {

  @Test
  public void sePuedeCrearContribuyenteAnonimo() {
    Contribuyente contribuyente = Contribuyente.crearContribuyenteAnonimo();
    assertNotNull(contribuyente);
  }

  @Test
  public void sePuedeCrearContribuyenteIdentificadoConNombre() {
    Contribuyente contribuyente = Contribuyente.crearContribuyente("Juan");
    assertNotNull(contribuyente);
  }

  @Test
  public void sePuedeCrearContribuyenteConNombreApellidoYEdad() {
    Contribuyente contribuyente = Contribuyente.crearContribuyente("Ana", "García", "32");
    assertNotNull(contribuyente);
  }
}
