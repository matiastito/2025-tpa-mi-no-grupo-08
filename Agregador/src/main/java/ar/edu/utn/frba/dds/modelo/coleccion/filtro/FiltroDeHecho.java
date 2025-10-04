package ar.edu.utn.frba.dds.modelo.coleccion.filtro;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "FILTRO_DE_HECHO_TIPO",
    discriminatorType = STRING)
public abstract class FiltroDeHecho {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  public long getId() {
    return id;
  }

  public abstract boolean filtrar(Hecho hecho);
}
