package ar.edu.utn.frba.dds.modelo.hecho.contenido;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTENIDO_MULTIMEDIA")
public class ContenidoMultimedia {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Enumerated(STRING)
  private TipoContenidoMultimedia tipoContenidoMultimedia;

  @Column(name = "URI")
  private String uri;

  public ContenidoMultimedia(TipoContenidoMultimedia tipoContenidoMultimedia, String uri) {
    this.tipoContenidoMultimedia = tipoContenidoMultimedia;
    this.uri = uri;
  }

  public TipoContenidoMultimedia getTipoContenidoMultimedia() {
    return tipoContenidoMultimedia;
  }
}
