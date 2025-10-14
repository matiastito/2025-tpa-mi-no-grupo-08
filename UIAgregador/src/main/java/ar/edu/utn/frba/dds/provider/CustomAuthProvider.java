package ar.edu.utn.frba.dds.provider;

import static org.slf4j.LoggerFactory.getLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ar.edu.utn.frba.dds.model.dto.auth.AuthResponseDTO;
import ar.edu.utn.frba.dds.model.dto.auth.RolesPermisosDTO;
import ar.edu.utn.frba.dds.servicio.ExternalAuthService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
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
    String password = String.valueOf(authentication.getCredentials());

    try {
      AuthResponseDTO authResponse = externalAuthService.login(username, password);
      if (authResponse == null) {
        throw new BadCredentialsException("Usuario o contraseña inválidos");
      }

      var authorities = new ArrayList<GrantedAuthority>();
      return new UsernamePasswordAuthenticationToken(username, null, authorities);

    } catch (org.springframework.web.reactive.function.client.WebClientResponseException e) {
      // 401/404 => credenciales inválidas
      if (e.getStatusCode().is4xxClientError()) {
        throw new BadCredentialsException("Usuario o contraseña inválidos");
      }
      // otros problemas => error del sistema
      throw new AuthenticationServiceException("Fallo del servicio de autenticación", e);
    } catch (AuthenticationException e) {
      throw e;
    } catch (Exception e) {
      throw new AuthenticationServiceException("Error del sistema de autenticación", e);
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

}