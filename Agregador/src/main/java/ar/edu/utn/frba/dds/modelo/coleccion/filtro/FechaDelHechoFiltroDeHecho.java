package ar.edu.utn.frba.dds.modelo.coleccion.filtro;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;


@Entity
@DiscriminatorValue("FECHA_DEL_HECHO_FILTRO_DE_HECHO")
public class FechaDelHechoFiltroDeHecho extends FiltroDeHecho {

  @Column(name = "FECHA_DEL_HECHO_DESDE", nullable = false)
  private LocalDateTime fechaDelHechoDesde;
  @Column(name = "FECHA_DEL_HECHO_HASTA", nullable = false)
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
