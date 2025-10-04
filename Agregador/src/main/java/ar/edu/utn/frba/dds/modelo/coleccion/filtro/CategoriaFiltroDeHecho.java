package ar.edu.utn.frba.dds.modelo.coleccion.filtro;

import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("CATEGORIA_FILTRO_DE_HECHO")
public class CategoriaFiltroDeHecho extends FiltroDeHecho {

  @ManyToOne
  @JoinColumn(name = "CATEGORIA_ID")
  private final Categoria categoria;

  
  public CategoriaFiltroDeHecho(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean filtrar(Hecho hecho) {
    return hecho.getCategoria().equals(this.categoria);
  }
}
