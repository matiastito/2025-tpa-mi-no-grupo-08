package ar.edu.utn.frba.dds.hecho;

public class SolicitudDeEliminacionDeHecho {
  private Hecho hecho;
  private String motivo;

  public SolicitudDeEliminacionDeHecho(Hecho hecho,
                                       String motivo) {
    this.hecho = hecho;
    this.motivo = motivo;
  }

  public void rechazar() {
    this.hecho.getSolicitudesDeEliminacionPendientes().remove(this);
  }

  public void aceptar() {
    this.hecho.getSolicitudesDeEliminacionPendientes().remove(this);
    this.hecho.eliminar();
  }
}
