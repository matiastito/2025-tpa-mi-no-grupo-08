package ar.edu.utn.frba.dds.service;

import ar.edu.utn.frba.dds.dto.UserRolesPermissionsDTO;
import ar.edu.utn.frba.dds.exception.NotFoundException;
import ar.edu.utn.frba.dds.model.Usuario;
import ar.edu.utn.frba.dds.repository.UsuariosRepository;
import ar.edu.utn.frba.dds.util.JWTUtil;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private final UsuariosRepository usuariosRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public LoginService(UsuariosRepository usuariosRepository) {
    this.usuariosRepository = usuariosRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public Usuario autenticarUsuario(String username, String password) {
    Optional<Usuario> usuarioOpt = usuariosRepository.findByNombreDeUsuario(username);

    if (usuarioOpt.isEmpty()) {
      throw new NotFoundException("Usuario", username);
    }

    Usuario usuario = usuarioOpt.get();

    // Verificar la contraseña usando BCrypt
    if (!passwordEncoder.matches(password, usuario.getContrasenia())) {
      throw new NotFoundException("Usuario", username);
    }

    return usuario;
  }

  public String generarAccessToken(String username) {
    return JWTUtil.generarAccessToken(username);
  }

  public String generarRefreshToken(String username) {
    return JWTUtil.generarRefreshToken(username);
  }

  public UserRolesPermissionsDTO obtenerRolesYPermisosUsuario(String username) {
    Optional<Usuario> usuarioOpt = usuariosRepository.findByNombreDeUsuario(username);

    if (usuarioOpt.isEmpty()) {
      throw new NotFoundException("Usuario", username);
    }

    Usuario usuario = usuarioOpt.get();

    return UserRolesPermissionsDTO.builder()
        .username(usuario.getNombreDeUsuario())
        .rol(usuario.getRol())
        .build();
  }
}
