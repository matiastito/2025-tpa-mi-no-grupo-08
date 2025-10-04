package ar.edu.utn.frba.dds.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {
  private Long id;
  private String nombre;
  private String nombreDeUsuario;
  private String contrasenia;
  private Rol rol;
}
