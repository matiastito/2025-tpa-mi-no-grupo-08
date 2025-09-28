package ar.edu.utn.frba.dds.modelo.colaborador;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDate.now;
import static java.time.Period.between;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "CONTRIBUYENTE")
public class Contribuyente {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Column(name = "NOMBRE", nullable = false)
  private String nombre;
  @Column(name = "APELLIDO", nullable = false)
  private String apellido;
  @Column(name = "FECHA_DE_NACIMIENTO")
  private LocalDate fechaNacimiento;

  public Contribuyente() {
  }

  public Contribuyente(String nombre) {
    this.nombre = nombre;
  }

  public Contribuyente(String nombre, String apellido) {
    this.nombre = nombre;
    this.apellido = apellido;
  }

  public Contribuyente(String nombre, String apellido, LocalDate fechaNacimiento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getApellido() {
    return apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public Integer getEdad() {
    return between(now(), fechaNacimiento).getYears();
  }
}
