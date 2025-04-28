package ar.edu.utn.frba.dds.coleccion.filtro;

import ar.edu.utn.frba.dds.hecho.Hecho;

import java.time.LocalDateTime;

public class FechaDelHechoFiltroDeHecho implements FiltroDeHecho {

  private LocalDateTime fechaDelHechoDesde;
  private LocalDateTime fechaDelHechoHasta;

  public FechaDelHechoFiltroDeHecho(LocalDateTime fechaDelHechoDesde, LocalDateTime fechaDelHechoHasta) {
    this.fechaDelHechoDesde = fechaDelHechoDesde;
    this.fechaDelHechoHasta = fechaDelHechoHasta;
  }

  @Override
  public boolean filtrar(Hecho hecho) {
    return hecho.getFechaDelHecho().isBefore(fechaDelHechoHasta) && hecho.getFechaDelHecho().isAfter(fechaDelHechoDesde);
  }
}
