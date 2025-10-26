package ar.edu.utn.frba.dds.web.controlador.dto;

import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;

import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HechoDTO {
  private Long id;
  private HechoOrigen hechoOrigen;
  private String titulo;
  private String descripcion;
  private String categoria;
  private ContribuyenteDTO contribuyenteDTO;
  // TODO
  //private ContenidoMultimediaDTO contenidoMultimedia;
  private LocalDate fechaDelHecho;
  private UbicacionDTO ubicacion;
  private LocalDateTime fechaDeCarga;
  private boolean eliminado;

  public HechoDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public void setFechaDeCarga(LocalDateTime fechaDeCarga) {
    this.fechaDeCarga = fechaDeCarga;
  }

  public void setUbicacion(UbicacionDTO ubicacion) {
    this.ubicacion = ubicacion;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public void setHechoOrigen(HechoOrigen hechoOrigen) {
    this.hechoOrigen = hechoOrigen;
  }

  public HechoOrigen getHechoOrigen() {
    return hechoOrigen;
  }

  public LocalDateTime getFechaDeCarga() {
    return fechaDeCarga;
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

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

  public void setContribuyenteDTO(ContribuyenteDTO contribuyenteDTO) {
    this.contribuyenteDTO = contribuyenteDTO;
  }

  public ContribuyenteDTO getContribuyenteDTO() {
    return contribuyenteDTO;
  }

  public static HechoDTO toDTO(Hecho hecho) {
    HechoDTO hechoDTO = new HechoDTO();
    hechoDTO.id = hecho.getId();
    hechoDTO.titulo = hecho.getTitulo();
    hechoDTO.descripcion = hecho.getDescripcion();
    hechoDTO.hechoOrigen = hecho.getHechoOrigen();
    hechoDTO.fechaDelHecho = hecho.getFechaDelHecho();
    hechoDTO.categoria = hecho.getCategoria().getNombre();
    hechoDTO.fechaDeCarga = hecho.getFechaDeCarga();
    hechoDTO.ubicacion = hechoDTO.
        new UbicacionDTO(hecho.getUbicacion().getLatitud(), hecho.getUbicacion().getLongitud());
    hechoDTO.eliminado = hecho.estaEliminado();
    return hechoDTO;
  }

  public static Hecho toHecho(HechoDTO hechoDTO, Fuente fuente) {
    Hecho hecho = new Hecho(
        hechoDTO.hechoOrigen,
        hechoDTO.titulo,
        hechoDTO.descripcion,
        new Categoria(hechoDTO.categoria),
        hechoDTO.fechaDelHecho,
        crearUbicacion(hechoDTO.ubicacion.getLatitud(), hechoDTO.ubicacion.getLongitud()),
        hechoDTO.fechaDeCarga,
        fuente
    );
    if (hechoDTO.getId() != null) {
      hecho.setIdExterno(hechoDTO.getId());
    }
    return hecho;
  }

  private class UbicacionDTO {
    private String latitud;
    private String longitud;
    private String Provincia;

    public UbicacionDTO() {
    }

    public UbicacionDTO(String latitud, String longitud) {
      this.latitud = latitud;
      this.longitud = longitud;
    }

    public String getProvincia() {
      return Provincia;
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

    public void setProvincia(String provincia) {
      Provincia = provincia;
    }
  }
}
