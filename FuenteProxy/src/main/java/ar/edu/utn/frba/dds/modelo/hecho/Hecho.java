package ar.edu.utn.frba.dds.modelo.hecho;

public class Hecho {
  private String titulo;
  private boolean eliminado;

  public Hecho(String titulo) {
    this.titulo = titulo;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

  public boolean isEliminado() {
    return eliminado;
  }
}
