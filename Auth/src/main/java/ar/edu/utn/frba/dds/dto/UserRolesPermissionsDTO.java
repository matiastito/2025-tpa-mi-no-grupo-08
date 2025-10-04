package ar.edu.utn.frba.dds.dto;

import ar.edu.utn.frba.dds.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesPermissionsDTO {
  private String username;
  private Rol rol;
}
