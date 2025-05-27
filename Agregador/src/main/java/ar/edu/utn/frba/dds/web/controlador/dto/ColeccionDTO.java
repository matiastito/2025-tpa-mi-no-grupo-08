package ar.edu.utn.frba.dds.web.controlador.dto;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.fuente.TipoFuente;

public class ColeccionDTO {
  private String titulo;
  private String descripcion;
  private FuenteDTO fuenteDTO;

  public ColeccionDTO() {
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public FuenteDTO getFuenteDTO() {
    return fuenteDTO;
  }

  public void setFuente(FuenteDTO fuenteDTO) {
    this.fuenteDTO = fuenteDTO;
  }

  public class FuenteDTO {
    private String baseUrl;
    private TipoFuente tipoFuente;

    public FuenteDTO() {
    }

    public FuenteDTO(Fuente fuente) {
      this.baseUrl = fuente.getBaseUrl();
      this.tipoFuente = fuente.getTipoFuente();
    }
  }

  public static Coleccion toColeccion(ColeccionDTO coleccionDTO) {
    return new Coleccion(
        coleccionDTO.titulo,
        coleccionDTO.descripcion,
        new Fuente(coleccionDTO.getFuenteDTO().baseUrl,
            coleccionDTO.getFuenteDTO().tipoFuente));
  }

  public static ColeccionDTO toColeccionDTO(Coleccion coleccion) {
    ColeccionDTO coleccionDTO = new ColeccionDTO();
    coleccionDTO.titulo = coleccion.getTitulo();
    coleccionDTO.descripcion = coleccion.getDescripcion();
    coleccionDTO.fuenteDTO = coleccionDTO.new FuenteDTO(coleccion.getFuente());
    return coleccionDTO;
  }
}
