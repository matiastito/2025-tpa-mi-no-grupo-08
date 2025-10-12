package ar.edu.utn.frba.dds.controller;

import static ar.edu.utn.frba.dds.util.JWTUtil.generarAccessToken;
import static ar.edu.utn.frba.dds.util.JWTUtil.getKey;
import static ar.edu.utn.frba.dds.util.JWTUtil.validarToken;
import static io.jsonwebtoken.Jwts.parserBuilder;
import static java.util.Map.of;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import ar.edu.utn.frba.dds.dto.AuthResponseDTO;
import ar.edu.utn.frba.dds.dto.RefreshRequest;
import ar.edu.utn.frba.dds.dto.TokenResponse;
import ar.edu.utn.frba.dds.dto.UserDTO;
import ar.edu.utn.frba.dds.dto.UserRolesDTO;
import ar.edu.utn.frba.dds.exception.NotFoundException;
import ar.edu.utn.frba.dds.service.LoginService;
import io.jsonwebtoken.Claims;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private static final Logger log = getLogger(AuthController.class);
  private final LoginService loginService;

  @Autowired
  public AuthController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping
  public ResponseEntity<AuthResponseDTO> loginApi(@RequestBody Map<String, String> credentials) {
    try {
      String username = credentials.get("username");
      String password = credentials.get("password");

      // Validación básica de credenciales
      if (username == null || username.trim().isEmpty()
          || password == null || password.trim().isEmpty()) {
        return badRequest().build();
      }

      // Autenticar usuario usando el LoginService
      loginService.autenticarUsuario(username, password);

      // Generar tokens
      String accessToken = loginService.generarAccessToken(username);
      String refreshToken = loginService.generarRefreshToken(username);

      AuthResponseDTO response = AuthResponseDTO.builder()
          .accessToken(accessToken)
          .refreshToken(refreshToken).build();

      log.info("El usuario {} está logueado. El token generado es {}", username, accessToken);

      return ok(response);
    } catch (NotFoundException e) {
      return status(NOT_FOUND).build();
    } catch (Exception e) {
      return badRequest().build();
    }
  }

  @PostMapping("/refresh")
  public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshRequest request) {
    try {
      String username = validarToken(request.getRefreshToken());

      // Validar que el token sea de tipo refresh
      Claims claims = parserBuilder()
          .setSigningKey(getKey())
          .build()
          .parseClaimsJws(request.getRefreshToken())
          .getBody();

      if (!"refresh".equals(claims.get("type"))) {
        return badRequest().build();
      }

      String newAccessToken = generarAccessToken(username);
      TokenResponse response = new TokenResponse(newAccessToken, request.getRefreshToken());

      return ok(response);
    } catch (Exception e) {
      return badRequest().build();
    }
  }

  @PostMapping("/user")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
    try {
      if (userDTO.getUsername().isBlank() || userDTO.getPassword().isBlank() || userDTO.getNombre().isBlank()) {
        return badRequest().body("Faltan campos obligatorios");
      }

      // Delegamos la creación en el servicio
      boolean creado = loginService.registrarUsuario(
          userDTO.getNombre().trim(), userDTO.getUsername().trim(), userDTO.getPassword());
      if (!creado) {
        return status(409).body("El usuario ya existe");
      }

      return status(201).body(of(
          "username", userDTO.getUsername().trim(),
          "rol", "ROLE_CONTRIBUYENTE"
      ));
    } catch (Exception e) {
      return badRequest().build();
    }
  }

  @GetMapping("/user/roles")
  public ResponseEntity<UserRolesDTO> getUserRoles(Authentication authentication) {
    try {
      String username = authentication.getName();
      UserRolesDTO response = loginService.obtenerRolesUsuario(username);
      return ok(response);
    } catch (NotFoundException e) {
      log.error("Usuario no encontrado", e);
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      log.error("Error al obtener roles y permisos del usuario", e);
      return badRequest().build();
    }
  }
}
