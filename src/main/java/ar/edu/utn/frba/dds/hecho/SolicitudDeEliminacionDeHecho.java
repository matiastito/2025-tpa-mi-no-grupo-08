package ar.edu.utn.frba.dds.hecho;

import static ar.edu.utn.frba.dds.hecho.SolicitudDeEliminacionDeHechoEstado.ACEPTADA;
import static ar.edu.utn.frba.dds.hecho.SolicitudDeEliminacionDeHechoEstado.PENDIENTE;
import static ar.edu.utn.frba.dds.hecho.SolicitudDeEliminacionDeHechoEstado.RECHAZADA;
import static java.time.LocalDateTime.now;
import ar.edu.utn.frba.dds.administrador.Administrador;
import ar.edu.utn.frba.dds.colaborador.Contribuyente;

import java.time.LocalDateTime;

public class SolicitudDeEliminacionDeHecho {
  private SolicitudDeEliminacionDeHechoEstado solicitudDeEliminacionDeHechoEstado;
  private LocalDateTime fechaCreacion:
  private Hecho hecho;
  private String motivo;
  private Contribuyente repotador;
  private Administrador aprobador;
  private LocalDateTime fechaResolucion:

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

  private void resolver(SolicitudDeEliminacionDeHechoEstado aceptada, Administrador aprobador) {
    this.solicitudDeEliminacionDeHechoEstado = aceptada;
    this.hecho.getSolicitudesDeEliminacionPendientes().remove(this);
    this.aprobador = aprobador;
    this.fechaResolucion = now();
  }
}
