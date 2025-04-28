package ar.edu.utn.frba.dds.hecho;

import static ar.edu.utn.frba.dds.hecho.SolicitudDeEliminacionDeHechoEstado.ACEPTADA;
import static ar.edu.utn.frba.dds.hecho.SolicitudDeEliminacionDeHechoEstado.PENDIENTE;
import static ar.edu.utn.frba.dds.hecho.SolicitudDeEliminacionDeHechoEstado.RECHAZADA;
import ar.edu.utn.frba.dds.administrador.Administrador;
import ar.edu.utn.frba.dds.colaborador.Contribuyente;

public class SolicitudDeEliminacionDeHecho {
  private SolicitudDeEliminacionDeHechoEstado solicitudDeEliminacionDeHechoEstado;
  private Hecho hecho;
  private String motivo;
  private Contribuyente repotador;
  private Administrador aprobador;

  public SolicitudDeEliminacionDeHecho(Contribuyente reportadoPor, Hecho hecho, String motivo) {
    this.hecho = hecho;
    this.motivo = motivo;
    this.solicitudDeEliminacionDeHechoEstado = PENDIENTE;
  }

  public void aprobar(Administrador aprobador) {
    this.solicitudDeEliminacionDeHechoEstado = ACEPTADA;
    this.hecho.getSolicitudesDeEliminacionPendientes().remove(this);
    this.aprobador = aprobador;
    this.hecho.eliminar();
  }

  public void rechazar(Administrador aprobador) {
    this.solicitudDeEliminacionDeHechoEstado = RECHAZADA;
    this.hecho.getSolicitudesDeEliminacionPendientes().remove(this);
    this.aprobador = aprobador;
  }
}
