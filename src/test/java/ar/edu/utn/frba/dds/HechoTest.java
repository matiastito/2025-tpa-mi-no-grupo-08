package ar.edu.utn.frba.dds;

import static java.util.Set.of;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import ar.edu.utn.frba.dds.hecho.Hecho;
import org.junit.jupiter.api.Test;

public class HechoTest {
  @Test
  void unIngresateSoloPuedeCursarMateriasSinCorrelaivas() {
    Hecho hecho = new Hecho("Incendio");
    Hecho hechoElMismo = new Hecho("Incendio");
  }
}
