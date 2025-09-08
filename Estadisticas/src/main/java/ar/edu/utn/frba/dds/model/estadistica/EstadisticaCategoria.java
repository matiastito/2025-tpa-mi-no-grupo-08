package ar.edu.utn.frba.dds.model.estadistica;

import ar.edu.utn.frba.dds.model.Categoria;

import java.util.List;

public class EstadisticaCategoria {
  private Categoria categoria;
  private List<EstadisticaProvincia> estadisticaProvincias;
  private List<EstadisticaPorHora> estadisticaHora;
  private long cantidadDeHechos;

}
