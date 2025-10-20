package ar.edu.utn.frba.dds.web.controlador.dto;

import static java.util.stream.Collectors.toList;

import ar.edu.utn.frba.dds.consenso.TipoConsenso;
import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.fuente.TipoFuente;
import java.util.List;
import java.util.UUID;

public class ColeccionDTO {
  private Long id;
  private String titulo;
  private String descripcion;
  private List<FuenteDTO> fuentes;
  private UUID handle;
  private TipoConsenso tipoConsenso;

  public ColeccionDTO() {
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public UUID getHandle() {
    return handle;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public List<FuenteDTO> getFuentes() {
    return fuentes;
  }

  public void setHandle(UUID handle) {
    this.handle = handle;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setFuentes(List<FuenteDTO> fuentes) {
    this.fuentes = fuentes;
  }

  public TipoConsenso getTipoConsenso() {
    return tipoConsenso;
  }

  public void setTipoConsenso(TipoConsenso tipoConsenso) {
    this.tipoConsenso = tipoConsenso;
  }

  public static class FuenteDTO {
    private Long id;
    private String baseUrl;
    private TipoFuente tipoFuente;

    public FuenteDTO() {
    }

    public FuenteDTO(Long id, String baseUrl, TipoFuente tipoFuente) {
      this.id = id;
      this.baseUrl = baseUrl;
      this.tipoFuente = tipoFuente;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Long getId() {
      return id;
    }

    public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
    }

    public void setTipoFuente(TipoFuente tipoFuente) {
      this.tipoFuente = tipoFuente;
    }

    public TipoFuente getTipoFuente() {
      return tipoFuente;
    }

    public String getBaseUrl() {
      return baseUrl;
    }

    public static Fuente toModel(FuenteDTO fuenteDTO) {
      return new Fuente(fuenteDTO.getId(), fuenteDTO.getBaseUrl(), fuenteDTO.getTipoFuente());
    }

    public static List<FuenteDTO> toDTO(List<Fuente> fuentes) {
      return fuentes.stream().map(f ->
          new FuenteDTO(f.getId(), f.getBaseUrl(), f.getTipoFuente())).collect(toList());
    }
  }

  public static Coleccion toModel(ColeccionDTO coleccionDTO) {
    return new Coleccion(
        coleccionDTO.titulo,
        coleccionDTO.descripcion,
        coleccionDTO.fuentes.stream().map(f ->
            new Fuente(f.baseUrl, f.tipoFuente)).toList().toArray(new Fuente[0]));
  }

  public static ColeccionDTO toDTO(Coleccion coleccion) {
    ColeccionDTO coleccionDTO = new ColeccionDTO();
    coleccionDTO.id = coleccion.getId();
    coleccionDTO.titulo = coleccion.getTitulo();
    coleccionDTO.handle = coleccion.getHandle();
    coleccionDTO.tipoConsenso = coleccion.getTipoConsenso();
    coleccionDTO.descripcion = coleccion.getDescripcion();
    coleccionDTO.fuentes =
        coleccion.getFuentes().stream().map(f ->
            new FuenteDTO(f.getId(), f.getBaseUrl(), f.getTipoFuente())).collect(toList());
    return coleccionDTO;
  }
}
