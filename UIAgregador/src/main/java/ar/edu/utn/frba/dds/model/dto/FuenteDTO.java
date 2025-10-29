package ar.edu.utn.frba.dds.model.dto;

public class FuenteDTO {
    private Long id;
    private String baseUrl;
    private TipoFuente tipoFuente; // O String si no es Enum

    // Constructor vacío
    public FuenteDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
    public TipoFuente getTipoFuente() { return tipoFuente; }
    public void setTipoFuente(TipoFuente tipoFuente) { this.tipoFuente = tipoFuente; }
}