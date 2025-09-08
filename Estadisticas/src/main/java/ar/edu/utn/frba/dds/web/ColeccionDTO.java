package ar.edu.utn.frba.dds.web;

public class ColeccionDTO {
  private String titulo;
  private String descripcion;
  private String handle;

  public ColeccionDTO() {
  }

  public String getHandle() {
    return handle;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setHandle(String handle) {
    this.handle = handle;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }


  public static class FuenteDTO {
    private String baseUrl;

    public FuenteDTO() {
    }

    public FuenteDTO(String baseUrl) {
      this.baseUrl = baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
      return baseUrl;
    }
  }
}
