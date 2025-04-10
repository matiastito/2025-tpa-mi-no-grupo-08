package ar.edu.utn.frba.dds.hecho.origen;

import ar.edu.utn.frba.dds.colaborador.Contribuyente;

import java.util.Collection;

public class HechoOrigenColaborador implements HechoOrigen {
  private Contribuyente contribuyente;
  private Collection<String> etiquetas;

  @Override
  public Collection<String> etiquetas() {
    return etiquetas;
  }

  @Override
  public void etiquetar(String etiqueta) {
    etiquetas.add(etiqueta);
  }
}
