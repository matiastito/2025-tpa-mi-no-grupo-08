package ar.edu.utn.frba.dds.hecho.origen;

import static ar.edu.utn.frba.dds.hecho.origen.TipoHechoOrigen.COLABORADOR;
import ar.edu.utn.frba.dds.colaborador.Colaborador;

import java.util.Collection;

public class HechoOrigenColaborador implements HechoOrigen {
  private Colaborador colaborador;
  private Collection<String> etiquetas;

  @Override
  public TipoHechoOrigen getTipoHecho() {
    return COLABORADOR;
  }

  @Override
  public Collection<String> etiquetas() {
    return etiquetas;
  }

  @Override
  public void etiquetar(String etiqueta) {
    etiquetas.add(etiqueta);
  }
}
