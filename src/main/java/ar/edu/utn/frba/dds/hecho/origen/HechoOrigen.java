package ar.edu.utn.frba.dds.hecho.origen;

import java.util.Collection;

public interface HechoOrigen {
  TipoHechoOrigen getTipoHecho();
  Collection<String> etiquetas();
  void etiquetar(String etiqueta);
}
