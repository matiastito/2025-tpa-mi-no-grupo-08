package ar.edu.utn.frba.dds.modelo.coleccion.filtro;

import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.ALL;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "FILTROS_PARA_HECHO")
public class FiltrosParaHecho {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @OneToMany
  @JoinColumn(name = "FILTROS_PARA_HECHO_ID")
  @Cascade(ALL)
  private List<FiltroDeHecho> filtros;

  public FiltrosParaHecho() {
    this.filtros = new ArrayList<>();
  }

  public boolean aplicarFiltros(Hecho hecho) {
    return this.filtros.stream().allMatch(
        filtroDeHecho -> filtroDeHecho.filtrar(hecho));
  }

  public void agregarFiltro(FiltroDeHecho filtro) {
    this.filtros.add(filtro);
  }
}
