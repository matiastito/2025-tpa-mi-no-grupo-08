package ar.edu.utn.frba.dds.modelo.fuente;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import java.util.List;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "FUENTE_PROXY_TIPO",
    discriminatorType = STRING)
public abstract class FuenteProxy {
  @Id
  @GeneratedValue()
  private long id;

  public long getId() {
    return id;
  }

  public abstract List<HechoDTO> hechos();
}
