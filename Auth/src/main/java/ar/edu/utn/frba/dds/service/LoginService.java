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
    // Verificamos si ya existe un usuario con ese nombre de usuario
    Optional<Usuario> existente = usuariosRepository.findByNombreDeUsuario(username);
    if (existente.isPresent()) {
      return false;
    }

    // Creamos el nuevo usuario
    Usuario nuevo = new Usuario();
    nuevo.setNombre(nombre);
    nuevo.setNombreDeUsuario(username);
    nuevo.setContrasenia(passwordEncoder.encode(passwordPlano));
    nuevo.setRol(CONTRIBUYENTE);

    usuariosRepository.save(nuevo);
    return true;
  }

  public Usuario upsertFromSocial(String email, String nombre) {
    // Busco por nombreDeUsuario (en tu modelo, el "username")
    Optional<Usuario> existente = usuariosRepository.findByNombreDeUsuario(email);
    if (existente.isPresent()) {
      Usuario u = existente.get();
      // Si no tenía nombre, actualizo; podés ajustar esta lógica si querés
      if (u.getNombre() == null || u.getNombre().isBlank()) {
        u.setNombre(nombre);
        usuariosRepository.save(u);
      }
      return u;
    }

    // Si no existe, lo creo con rol CONTRIBUYENTE por defecto
    Usuario nuevo = new Usuario();
    nuevo.setNombre(nombre);
    nuevo.setNombreDeUsuario(email);

    // Como es social login, no tenemos password real. Guardamos un hash dummy.
    String dummy = "oauth2:" + email;
    nuevo.setContrasenia(passwordEncoder.encode(dummy));

    nuevo.setRol(CONTRIBUYENTE);
    usuariosRepository.save(nuevo);
    return nuevo;
  }

}
