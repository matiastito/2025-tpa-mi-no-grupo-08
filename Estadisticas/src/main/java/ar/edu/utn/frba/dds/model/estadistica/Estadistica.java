package ar.edu.utn.frba.dds.model.estadistica;

import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.ALL;

import ar.edu.utn.frba.dds.model.Categoria;
import ar.edu.utn.frba.dds.model.Provincia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "ESTADISTICA")
public class Estadistica {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column(name = "FECHA_DE_CREACION", nullable = false)
  private LocalDate fechaCreacion;

  @OneToMany
  @Cascade(ALL)
  private List<EstadisticaColeccion> estadisticasColecciones = new ArrayList<>();
  @OneToMany
  @Cascade(ALL)
  private List<EstadisticaCategoria> estadisticasCategoria = new ArrayList<>();
  @OneToMany
  @Cascade(ALL)
  private List<EstadisticaSolicitudes> estadisticasSolicitudes = new ArrayList<>();

  public Estadistica() {

  }

  public Estadistica(LocalDate fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public void registarHecho(Categoria categoria, Provincia provincia, int horaDelHecho) {
    Optional<EstadisticaCategoria> estadisticaCategoria = estadisticasCategoria.stream()
        .filter(ec -> ec.getCategoria().getId() == categoria.getId())
        .findFirst();
    if (estadisticaCategoria.isEmpty()) {
      EstadisticaCategoria ec = new EstadisticaCategoria(categoria);
      ec.registrarHecho(provincia, horaDelHecho);
      estadisticasCategoria.add(ec);
    } else {
      estadisticaCategoria.get().registrarHecho(provincia, horaDelHecho);
    }

  }

  public void registrarHecho(Provincia provincia, String handle) {
    Optional<EstadisticaColeccion> estadisticaColeccion = estadisticasColecciones.stream()
        .filter(ec -> ec.getHandle().equalsIgnoreCase(handle)).findFirst();
    if (estadisticaColeccion.isEmpty()) {
      EstadisticaColeccion ec = new EstadisticaColeccion(handle);
      ec.registrarHecho(provincia);
      estadisticasColecciones.add(ec);
    }
  }

  public void registrarCantidadDeSolicitudesRechazadasPorSpam(int rechazadaPorSpam) {
    estadisticasSolicitudes.add(new EstadisticaSolicitudes(rechazadaPorSpam));
  }

  public List<EstadisticaCategoria> getEstadisticasCategoria() {
    return estadisticasCategoria;
  }

  public List<EstadisticaColeccion> getEstadisticasColecciones() {
    return estadisticasColecciones;
  }

  public List<EstadisticaSolicitudes> getEstadisticasSolicitudes() {
    return estadisticasSolicitudes;
  }
}
