package ar.edu.utn.frba.dds.util.archivo;

public enum TipoArchivo {
  CSV(".csv"), JSON(".json"), TXT(".txt");

  private final String extension;

  TipoArchivo(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }
}
