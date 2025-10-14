package ar.edu.utn.frba.dds.oauth;

import ar.edu.utn.frba.dds.util.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

  private final JWTUtil jwtUtil;

  @Value("${app.ui.redirect-uri:http://localhost:8080/}")
  private String uiRedirect;

  public OAuth2SuccessHandler(JWTUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) throws IOException {
    OAuth2User principal = (OAuth2User) authentication.getPrincipal();
    Map<String, Object> attrs = principal.getAttributes();

    String email = (String) attrs.getOrDefault("email", "");
    String name  = (String) attrs.getOrDefault("name", email);

    // TODO: resolver/crear usuario y rol real según tu modelo
    String role = resolveUserRole(email); // "ROLE_ADMIN" o "ROLE_CONTRIBUYENTE"

    String accessToken = jwtUtil.generarAccessToken(email, role, name);

    Cookie cookie = new Cookie("JWT", accessToken);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    // En localhost con HTTP: NO uses Secure ni SameSite=None (puede descartarla el navegador).
    // En HTTPS real: cookie.setSecure(true) y SameSite=None.

    response.addCookie(cookie);
    response.sendRedirect(uiRedirect);
  }

  private String resolveUserRole(String email) {
    if (email != null && email.endsWith("@utn.edu.ar")) return "ROLE_ADMIN";
    return "ROLE_CONTRIBUYENTE";
  }
}



