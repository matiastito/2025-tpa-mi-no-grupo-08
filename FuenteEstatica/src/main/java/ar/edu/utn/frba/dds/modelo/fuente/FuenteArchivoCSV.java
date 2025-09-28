package ar.edu.utn.frba.dds.modelo.fuente;

import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.ALL;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.servicio.ImportadorDeHechosDesdeArchivo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "FUENTE_ARCHIVO_CSV")
public class FuenteArchivoCSV {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Column(name = "URI", nullable = false)
  private String uri;
  @Column(name = "PROCESADO", nullable = false)
  private boolean procesado;
  @OneToMany
  @JoinColumn(name = "FUENTE_ID")
  @Cascade(ALL)
  private Set<Hecho> hechos;

  public FuenteArchivoCSV() {
  }

  public FuenteArchivoCSV(String uri) {
    this.uri = uri;
    this.procesado = false;
    hechos = new HashSet<>();
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getUri() {
    return uri;
  }

  public void marcarComoProcesado() {
    this.procesado = true;
  }

  public void agregarHecho(Hecho hecho) {
    if (hechos.contains(hecho)) {
      hechos.remove(hecho);
    }
    hechos.add(hecho);
  }

  public Set<Hecho> hechos() {
    return hechos;
  }

  public void importar(ImportadorDeHechosDesdeArchivo importadorDeHechosDesdeArchivo) {
    importadorDeHechosDesdeArchivo.importarHechos(this);
    this.marcarComoProcesado();
  }
}
