package ar.edu.utn.frba.dds.service;

import static ar.edu.utn.frba.dds.model.Rol.CONTRIBUYENTE;

import ar.edu.utn.frba.dds.dto.UserRolesDTO;
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
  private final JWTUtil jwtUtil; // <-- inyectamos el bean

  public LoginService(UsuariosRepository usuariosRepository, JWTUtil jwtUtil) {
    this.usuariosRepository = usuariosRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();
    this.jwtUtil = jwtUtil; // <-- guardamos la instancia
  }

  public Usuario autenticarUsuario(String username, String password) {
    Optional<Usuario> usuarioOpt = usuariosRepository.findByNombreDeUsuario(username);

    if (usuarioOpt.isEmpty()) {
      throw new NotFoundException("Usuario", username);
    }

    Usuario usuario = usuarioOpt.get();

    if (!passwordEncoder.matches(password, usuario.getContrasenia())) {
      throw new NotFoundException("Usuario", username);
    }

    return usuario;
  }

  public String generarAccessToken(String username) {
    // ANTES: return JWTUtil.generarAccessToken(username);
    return jwtUtil.generarAccessToken(username); // <-- instancia
  }

  public String generarRefreshToken(String username) {
    // ANTES: return JWTUtil.generarRefreshToken(username);
    return jwtUtil.generarRefreshToken(username); // <-- instancia
  }

  public UserRolesDTO obtenerRolesUsuario(String username) {
    Optional<Usuario> usuarioOpt = usuariosRepository.findByNombreDeUsuario(username);

    if (usuarioOpt.isEmpty()) {
      throw new NotFoundException("Usuario", username);
    }

    Usuario usuario = usuarioOpt.get();

    return UserRolesDTO.builder()
        .username(usuario.getNombreDeUsuario())
        .rol(usuario.getRol())
        .build();
  }

  public boolean registrarUsuario(String nombre, String username, String passwordPlano) {
    Optional<Usuario> existente = usuariosRepository.findByNombreDeUsuario(username);
    if (existente.isPresent()) {
      return false;
    }

    Usuario nuevo = new Usuario();
    nuevo.setNombre(nombre);
    nuevo.setNombreDeUsuario(username);
    nuevo.setContrasenia(passwordEncoder.encode(passwordPlano));
    nuevo.setRol(CONTRIBUYENTE);

    usuariosRepository.save(nuevo);
    return true;
  }
}
