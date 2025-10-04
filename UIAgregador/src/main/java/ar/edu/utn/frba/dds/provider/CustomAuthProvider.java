package ar.edu.utn.frba.dds.provider;

import static org.slf4j.LoggerFactory.getLogger;

import ar.edu.utn.frba.dds.model.dto.auth.AuthResponseDTO;
import ar.edu.utn.frba.dds.model.dto.auth.RolesPermisosDTO;
import ar.edu.utn.frba.dds.servicio.ExternalAuthService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CustomAuthProvider implements AuthenticationProvider {
  private static final Logger log = getLogger(CustomAuthProvider.class);
  private final ExternalAuthService externalAuthService;

  public CustomAuthProvider(ExternalAuthService externalAuthService) {
    this.externalAuthService = externalAuthService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    try {
      // Llamada a servicio externo para obtener tokens
      AuthResponseDTO authResponse = externalAuthService.login(username, password);

      if (authResponse == null) {
        throw new BadCredentialsException("Usuario o contraseña inválidos");
      }

      log.info("Usuario logeado! Configurando variables de sesión");
      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpServletRequest request = attributes.getRequest();

      request.getSession().setAttribute("accessToken", authResponse.getAccessToken());
      request.getSession().setAttribute("refreshToken", authResponse.getRefreshToken());
      request.getSession().setAttribute("username", username);

      log.info("Buscando roles y permisos del usuario");
      RolesPermisosDTO rolesPermisos =
          externalAuthService.getRoles(authResponse.getAccessToken());

      log.info("Cargando roles y permisos del usuario en sesión");
      request.getSession().setAttribute("rol", rolesPermisos.getRol());

      List<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("ROLE_" + rolesPermisos.getRol().name()));

      return new UsernamePasswordAuthenticationToken(username, password, authorities);

    } catch (RuntimeException e) {
      throw new BadCredentialsException("Error en el sistema de autenticación: " + e.getMessage());
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}