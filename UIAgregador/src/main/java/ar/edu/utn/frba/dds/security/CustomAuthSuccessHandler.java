package ar.edu.utn.frba.dds.security;

import ar.edu.utn.frba.dds.servicio.ExternalAuthService;
import ar.edu.utn.frba.dds.model.dto.auth.AuthResponseDTO;
import ar.edu.utn.frba.dds.model.dto.auth.RolesPermisosDTO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired ExternalAuthService externalAuthService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) throws IOException {
    try {
      String username = (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User u)
          ? u.getUsername() : String.valueOf(authentication.getPrincipal());

      AuthResponseDTO auth = externalAuthService.login(username, null);

      request.getSession(true).setAttribute("accessToken", auth.getAccessToken());
      request.getSession().setAttribute("refreshToken", auth.getRefreshToken());
      request.getSession().setAttribute("username", username);

      RolesPermisosDTO roles = externalAuthService.getRoles(auth.getAccessToken());
      request.getSession().setAttribute("rol", roles.getRol());

    } catch (Exception e) {
      log.error("Post-login error configurando sesión/roles", e);
      response.sendRedirect("/home");
      return;
    }
    response.sendRedirect("/home");
  }
}
