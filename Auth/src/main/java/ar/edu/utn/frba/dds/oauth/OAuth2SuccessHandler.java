package ar.edu.utn.frba.dds.oauth;

import ar.edu.utn.frba.dds.util.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

  private static final Logger log = LoggerFactory.getLogger(OAuth2SuccessHandler.class);
  private final JWTUtil jwtUtil;

  @Value("${app.ui.redirect-uri:http://localhost:9000}")
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

    // Siempre rol contribuyente en SSO
    String role = "ROLE_CONTRIBUYENTE";


    String accessToken = jwtUtil.generarAccessToken(email, role, name);

    Cookie cookie = new Cookie("JWT", accessToken);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    response.addCookie(cookie);

    String base = uiRedirect.endsWith("/") ? uiRedirect.substring(0, uiRedirect.length()-1) : uiRedirect;
    String redirect = base + "/sso/callback?token=" + URLEncoder.encode(accessToken, StandardCharsets.UTF_8);

    log.info("[Auth] SSO OK para {}. Redirigiendo a: {}", email, redirect); 
    response.sendRedirect(redirect);
  }

}



