package ar.edu.utn.frba.dds.modelo.hecho;

import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.ACEPTADA;
import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.PENDIENTE;
import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.RECHAZADA;
import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.RECHAZADA_POR_SPAM;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "SOLICITUD_DE_ELIMINACION_HECHO")
public class SolicitudDeEliminacionDeHecho {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @ManyToOne
  @JoinColumn(name = "HECHO_ID")
  private Hecho hecho;
  @Enumerated(STRING)
  private SolicitudDeEliminacionDeHechoEstado solicitudDeEliminacionDeHechoEstado;
  @Column(name = "FECHA_DE_CREACION", nullable = false)
  private LocalDateTime fechaCreacion;
  @Column(name = "MOTIVO", nullable = false)
  private String motivo;
  @ManyToOne
  @JoinColumn(name = "REPORTADOR_ID")
  private Contribuyente repotador;
  @ManyToOne
  @JoinColumn(name = "APROBADOR_ID")
  private Administrador aprobador;
  @Column(name = "FECHA_DE_RESOLUCION", nullable = false)
  private LocalDateTime fechaResolucion;

  public SolicitudDeEliminacionDeHecho() {
  }

  public SolicitudDeEliminacionDeHecho(Contribuyente reportadoPor, Hecho hecho, String motivo) {
    this.hecho = hecho;
    this.motivo = motivo;
    this.fechaCreacion = now();
    this.solicitudDeEliminacionDeHechoEstado = PENDIENTE;
  }

  public void aprobar(Administrador aprobador) {
    resolver(ACEPTADA, aprobador);
    this.hecho.eliminar(this);
  }

  public void rechazar(Administrador aprobador) {
    resolver(RECHAZADA, aprobador);
  }

  public void rechazar() {
    resolver(RECHAZADA);
  }

  public void rechazarPorSpam() {
    resolver(RECHAZADA_POR_SPAM);
  }

  private void resolver(SolicitudDeEliminacionDeHechoEstado estado, Administrador aprobador) {
    this.aprobador = aprobador;
    resolver(estado);
  }

  public Hecho getHecho() {
    return hecho;
  }

  public Contribuyente getRepotador() {
    return repotador;
  }

  private void resolver(SolicitudDeEliminacionDeHechoEstado estado) {
    this.solicitudDeEliminacionDeHechoEstado = estado;
    this.fechaResolucion = now();
  }

  public SolicitudDeEliminacionDeHechoEstado getEstado() {
    return solicitudDeEliminacionDeHechoEstado;
  }

  public String getMotivo() {
    return motivo;
  }
}
