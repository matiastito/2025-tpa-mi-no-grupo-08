package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.hecho.Hecho;

public class SolicitudDeEliminacionDeHecho {
  private Hecho hecho;
  private String motivo;

  public SolicitudDeEliminacionDeHecho(Hecho hecho, String motivo) {
    this.hecho = hecho;
    this.motivo = motivo;
  }

  public void aceptar() {
      this.hecho.eliminar();
  }
}
