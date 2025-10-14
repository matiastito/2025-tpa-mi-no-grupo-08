package ar.edu.utn.frba.dds.controller;

import ar.edu.utn.frba.dds.dto.AuthResponseDTO;
import ar.edu.utn.frba.dds.dto.RefreshRequest;
import ar.edu.utn.frba.dds.dto.TokenResponse;
import ar.edu.utn.frba.dds.dto.UserDTO;
import ar.edu.utn.frba.dds.dto.UserRolesDTO;
import ar.edu.utn.frba.dds.exception.NotFoundException;
import ar.edu.utn.frba.dds.service.LoginService;
import ar.edu.utn.frba.dds.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Map.of;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  private final LoginService loginService;
  private final JWTUtil jwtUtil;

  @Autowired
  public AuthController(LoginService loginService, JWTUtil jwtUtil) {
    this.loginService = loginService;
    this.jwtUtil = jwtUtil;
  }

  /**
   * Login clásico (usuario/contraseña).
   * Devuelve tokens por JSON y además setea cookie HttpOnly "JWT" con el access token
   * (mismo mecanismo que usamos en SSO para que el UI detecte sesión).
   */
  @PostMapping
  public ResponseEntity<AuthResponseDTO> loginApi(@RequestBody Map<String, String> credentials,
                                                  HttpServletResponse response) {
    try {
      String username = credentials.get("username");
      String password = credentials.get("password");

      if (username == null || username.isBlank() || password == null || password.isBlank()) {
        return badRequest().build();
      }

      // Autenticar usuario
      loginService.autenticarUsuario(username, password);

      // Datos visibles y rol (ajustá según tu modelo real)
      String name = username;                
      String role = "ROLE_CONTRIBUYENTE";

      // Generar tokens
      String accessToken  = jwtUtil.generarAccessToken(
          username,
          role != null ? role : "ROLE_CONTRIBUYENTE",
          name != null ? name : username
      );
      String refreshToken = jwtUtil.generarRefreshToken(username);

      // Cookie con el access token (para que el filtro JWT te deje autenticada en el UI)
      Cookie cookie = new Cookie("JWT", accessToken);
      cookie.setHttpOnly(true);
      cookie.setPath("/");
      // En HTTPS real: cookie.setSecure(true) y SameSite=None (si tu JDK lo soporta)
      response.addCookie(cookie);

      AuthResponseDTO body = AuthResponseDTO.builder()
          .accessToken(accessToken)
          .refreshToken(refreshToken)
          .build();

      log.info("Usuario {} logueado. Access token emitido.", username);
      return ok(body);

    } catch (NotFoundException e) {
      return status(NOT_FOUND).build();
    } catch (Exception e) {
      log.error("Error en login", e);
      return badRequest().build();
    }
  }

  /**
   * Emite un nuevo access token a partir de un refresh válido.
   * Valida que el claim "type" sea "refresh".
   * También actualiza la cookie "JWT" para mantener la sesión en el UI.
   */
  @PostMapping("/refresh")
  public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshRequest request,
                                               HttpServletResponse response) {
    try {
      String refreshToken = request.getRefreshToken();
      if (refreshToken == null || refreshToken.isBlank()) {
        return badRequest().build();
      }

      Jws<Claims> jws = jwtUtil.parse(refreshToken);
      Claims claims = jws.getBody();

      if (!"refresh".equals(claims.get("type", String.class))) {
        return badRequest().build();
      }

      String username = claims.getSubject();

      String name = username;                
      String role = "ROLE_CONTRIBUYENTE";

      String newAccessToken = jwtUtil.generarAccessToken(
          username,
          role != null ? role : "ROLE_CONTRIBUYENTE",
          name != null ? name : username
      );

      // Actualizar cookie con el nuevo access token
      Cookie cookie = new Cookie("JWT", newAccessToken);
      cookie.setHttpOnly(true);
      cookie.setPath("/");
      response.addCookie(cookie);

      TokenResponse body = new TokenResponse(newAccessToken, refreshToken);
      return ok(body);

    } catch (Exception e) {
      log.error("Error en refresh", e);
      return badRequest().build();
    }
  }

  /**
   * Registro básico de usuario.
   */
  @PostMapping("/user")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
    try {
      if (userDTO.getUsername() == null || userDTO.getUsername().isBlank()
          || userDTO.getPassword() == null || userDTO.getPassword().isBlank()
          || userDTO.getNombre() == null || userDTO.getNombre().isBlank()) {
        return badRequest().body("Faltan campos obligatorios");
      }

      boolean creado = loginService.registrarUsuario(
          userDTO.getNombre().trim(),
          userDTO.getUsername().trim(),
          userDTO.getPassword()
      );

      if (!creado) {
        return status(409).body("El usuario ya existe");
      }

      // Por defecto, asignamos contribuyente (ajustá si corresponde)
      return status(201).body(of(
          "username", userDTO.getUsername().trim(),
          "rol", "ROLE_CONTRIBUYENTE"
      ));

    } catch (Exception e) {
      log.error("Error al registrar usuario", e);
      return badRequest().build();
    }
  }

  /**
   * Roles/permisos del usuario autenticado.
   */
  @GetMapping("/user/roles")
  public ResponseEntity<UserRolesDTO> getUserRoles(org.springframework.security.core.Authentication authentication) {
    try {
      String username = authentication.getName();
      UserRolesDTO response = loginService.obtenerRolesUsuario(username);
      return ok(response);
    } catch (NotFoundException e) {
      log.error("Usuario no encontrado", e);
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      log.error("Error al obtener roles", e);
      return badRequest().build();
    }
  }
}
