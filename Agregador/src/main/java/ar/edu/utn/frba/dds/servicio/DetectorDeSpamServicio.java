package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;

public interface DetectorDeSpamServicio {
  void rechazaSpam(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho);
}
