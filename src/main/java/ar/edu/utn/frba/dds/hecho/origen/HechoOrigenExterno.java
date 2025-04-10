package ar.edu.utn.frba.dds.hecho.origen;

import static ar.edu.utn.frba.dds.hecho.origen.TipoHechoOrigen.COLABORADOR;
import static ar.edu.utn.frba.dds.hecho.origen.TipoHechoOrigen.EXTERNO;
import static java.util.Set.of;

import java.util.Collection;

public class HechoOrigenExterno implements HechoOrigen {
  @Override
  public TipoHechoOrigen getTipoHecho() {
    return EXTERNO;
  }

  @Override
  public Collection<String> etiquetas() {
    return of();
  }

  @Override
  public void etiquetar(String etiqueta) {
  }
}
