package ar.edu.utn.frba.dds.model.estadistica;

import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.ALL;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;

/*
#Hechos porProvincia porColeccion De una colección, ¿en qué provincia se agrupan la mayor cantidad de hechos reportados?

#Hechos porCategoria              ¿Cuál es la categoría con mayor cantidad de hechos reportados?
#Hechos porProvincia porCategoria ¿En qué provincia se presenta la mayor cantidad de hechos de una cierta categoría?
#Hechos porHora porCategoria      ¿A qué hora del día ocurren la mayor cantidad de hechos de una cierta categoría?
#SolicirudesSpam                  ¿Cuántas solicitudes de eliminación son spam?
*/
@Entity
@Table(name = "ESTADISTICA")
public class Estadistica {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  private LocalDate fechaCreacion;

  @OneToMany
  @Cascade(ALL)
  private List<EstadisticaColeccion> estadisticasColecciones;
  @OneToMany
  @Cascade(ALL)
  private List<EstadisticaCategoria> estadisticasCategoria;
  @OneToMany
  @Cascade(ALL)
  private List<EstadisticaSolicitudes> estadisticasSolicitudes;

}
