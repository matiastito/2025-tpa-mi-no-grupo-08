package ar.edu.utn.frba.dds.modelo.coleccion.filtro;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TITULO_FILTRO_DE_HECHO")
public class TituloFiltroDeHecho extends FiltroDeHecho {

  @Column(name = "TITULO", nullable = false)
  private final String titulo;

  public TituloFiltroDeHecho(String titulo) {
    this.titulo = titulo;
  }

  @Override
  public boolean filtrar(Hecho hecho) {
    return hecho.getTitulo().equalsIgnoreCase(this.titulo);
  }
}
