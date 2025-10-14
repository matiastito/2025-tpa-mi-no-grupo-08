package ar.edu.utn.frba.dds.oauth;

import ar.edu.utn.frba.dds.model.Usuario;
import ar.edu.utn.frba.dds.service.LoginService;
import ar.edu.utn.frba.dds.util.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  private LoginService loginService;

  @Value("${ui.redirect.success:http://localhost:8082/}")
  private String uiRedirect;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication)
      throws IOException, ServletException {

    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

    String email = (String) oAuth2User.getAttributes().get("email");
    if (email == null) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST,
          "No se recibió el email del proveedor OAuth2");
      return;
    }

    String nombre = (String) oAuth2User.getAttributes().getOrDefault("name", email);

    // upsert en base local
    Usuario usuario = loginService.upsertFromSocial(email, nombre);

    // Generar access token
    String jwt = JWTUtil.generarAccessToken(usuario.getNombreDeUsuario());

    // Enviar JWT en cookie HttpOnly
    Cookie cookie = new Cookie("JWT", jwt);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    cookie.setMaxAge(60 * 60 * 4); 
    // cookie.setSecure(true); // activar en prod con HTTPS
    response.addCookie(cookie);
    // Redirigir a UI
    response.sendRedirect(uiRedirect);
  }
}


