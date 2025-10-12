package ar.edu.utn.frba.dds.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
  private String username;
  private String password;
  private String nombre;
}
