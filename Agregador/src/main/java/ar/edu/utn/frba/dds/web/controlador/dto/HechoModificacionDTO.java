package ar.edu.utn.frba.dds.web.controlador.dto;

import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;
import static java.util.stream.Collectors.toList;

import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.HechoModificacion;
import ar.edu.utn.frba.dds.modelo.hecho.HechoModificacionEstado;
import java.time.LocalDate;
import java.util.List;

public class HechoModificacionDTO {
  private Long id;
  private Long hechoId;
  private String titulo;
  private String descripcion;
  private String categoria;
  private ContribuyenteDTO contribuyenteDTO;
  private LocalDate fechaDelHecho;
  private UbicacionDTO ubicacion;
  private HechoModificacionEstado hechoModificacionEstado;

  public HechoModificacionDTO() {
  }

  public static List<HechoModificacionDTO> toDTO(List<HechoModificacion> hechosModificaciones) {
    return hechosModificaciones.stream().map(HechoModificacionDTO::toDTO).collect(toList());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setHechoId(Long hechoId) {
    this.hechoId = hechoId;
  }

  public Long getHechoId() {
    return hechoId;
  }

  public void setHechoModificacionEstado(HechoModificacionEstado hechoModificacionEstado) {
    this.hechoModificacionEstado = hechoModificacionEstado;
  }

  public HechoModificacionEstado getHechoModificacionEstado() {
    return hechoModificacionEstado;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setFechaDelHecho(LocalDate fechaDelHecho) {
    this.fechaDelHecho = fechaDelHecho;
  }

  public void setUbicacion(UbicacionDTO ubicacion) {
    this.ubicacion = ubicacion;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public String getTitulo() {
    return titulo;
  }

  public UbicacionDTO getUbicacion() {
    return ubicacion;
  }

  public String getCategoria() {
    return categoria;
  }

  public LocalDate getFechaDelHecho() {
    return fechaDelHecho;
  }

  public void setContribuyenteDTO(ContribuyenteDTO contribuyenteDTO) {
    this.contribuyenteDTO = contribuyenteDTO;
  }

  public ContribuyenteDTO getContribuyenteDTO() {
    return contribuyenteDTO;
  }

  public static HechoModificacionDTO toDTO(HechoModificacion hechoModificacion) {
    HechoModificacionDTO hechoModificacionDTO = new HechoModificacionDTO();
    hechoModificacionDTO.id = hechoModificacion.getId();
    hechoModificacionDTO.titulo = hechoModificacion.getTitulo();
    hechoModificacionDTO.descripcion = hechoModificacion.getDescripcion();
    hechoModificacionDTO.fechaDelHecho = hechoModificacion.getFechaDelHecho();
    hechoModificacionDTO.categoria = hechoModificacion.getCategoria().getNombre();
    hechoModificacionDTO.ubicacion = hechoModificacionDTO.
        new UbicacionDTO(hechoModificacion.getUbicacion().getLatitud(), hechoModificacion.getUbicacion().getLongitud());
    hechoModificacionDTO.hechoModificacionEstado = hechoModificacion.getHechoModificacionEstado();
    return hechoModificacionDTO;
  }

  public static HechoModificacion toHechoModificacion(HechoModificacionDTO hechoDTO) {
    return new HechoModificacion(
        hechoDTO.titulo,
        hechoDTO.descripcion,
        new Categoria(hechoDTO.categoria),
        hechoDTO.fechaDelHecho,
        crearUbicacion(hechoDTO.ubicacion.getLatitud(), hechoDTO.ubicacion.getLongitud())
    );
  }

  private class UbicacionDTO {
    private String latitud;
    private String longitud;

    public UbicacionDTO() {
    }

    public UbicacionDTO(String latitud, String longitud) {
      this.latitud = latitud;
      this.longitud = longitud;
    }


    public String getLatitud() {
      return latitud;
    }

    public String getLongitud() {
      return longitud;
    }

    public void setLongitud(String longitud) {
      this.longitud = longitud;
    }

    public void setLatitud(String latitud) {
      this.latitud = latitud;
    }
  }
}
