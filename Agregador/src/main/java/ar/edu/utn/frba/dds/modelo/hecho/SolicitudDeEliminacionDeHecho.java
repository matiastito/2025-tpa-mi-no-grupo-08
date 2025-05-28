package ar.edu.utn.frba.dds.modelo.hecho;

import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.ACEPTADA;
import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.PENDIENTE;
import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.RECHAZADA;
import static java.time.LocalDateTime.now;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import java.time.LocalDateTime;

public class SolicitudDeEliminacionDeHecho {
  private SolicitudDeEliminacionDeHechoEstado solicitudDeEliminacionDeHechoEstado;

  private Hecho hecho;

  private LocalDateTime fechaCreacion;
  private String motivo;
  private Contribuyente repotador;

  private Administrador aprobador;
  private LocalDateTime fechaResolucion;

  public SolicitudDeEliminacionDeHecho(Contribuyente reportadoPor, Hecho hecho, String motivo) {
    this.hecho = hecho;
    this.motivo = motivo;
    this.fechaCreacion = now();
    this.solicitudDeEliminacionDeHechoEstado = PENDIENTE;
  }

  public void aprobar(Administrador aprobador) {
    resolver(ACEPTADA, aprobador);
    this.hecho.eliminar();
  }

  public void rechazar(Administrador aprobador) {
    resolver(RECHAZADA, aprobador);
  }

  public void rechazar() {
    resolver(RECHAZADA);
  }

  private void resolver(SolicitudDeEliminacionDeHechoEstado estado, Administrador aprobador) {
    this.aprobador = aprobador;
    resolver(estado);
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
