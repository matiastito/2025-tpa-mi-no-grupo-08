package ar.edu.utn.frba.dds.hecho.origen;

import static java.util.Set.of;

import java.util.Collection;

public class HechoOrigenManual implements HechoOrigen {
  @Override
  public Collection<String> etiquetas() {
    return of();
  }

  @Override
  public void etiquetar(String etiqueta) {
  }
}
