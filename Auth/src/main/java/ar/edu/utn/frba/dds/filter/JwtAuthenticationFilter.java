package ar.edu.utn.frba.dds.filter;

import static ar.edu.utn.frba.dds.util.JWTUtil.validarToken;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static java.util.Collections.singletonList;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import ar.edu.utn.frba.dds.dto.UserRolesDTO;
import ar.edu.utn.frba.dds.service.LoginService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private LoginService loginService;

  public JwtAuthenticationFilter(@Autowired LoginService loginService) {
    this.loginService = loginService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    
    String token = extraerToken(request);
    
    if(token != null) {
      try {
        String username = validarToken(token);

        UserRolesDTO userRolesDTO = loginService.obtenerRolesUsuario(username);
        var auth = new UsernamePasswordAuthenticationToken(
            username,
            null,
            singletonList(new SimpleGrantedAuthority("ROLE_" + userRolesDTO.getRol().name()))
        );
        getContext().setAuthentication(auth);
      } catch (Exception e) {
        response.sendError(SC_UNAUTHORIZED, "Token inválido");
        return;
      }
    } else {
      System.out.println("No hay token de autorización");
    }

    filterChain.doFilter(request, response);
  }

  private String extraerToken(HttpServletRequest request) {
    // 1) Intentar por header Authorization: Bearer xxx
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      return header.substring(7);
    }

    // 2) Fallback: intentar por cookie "JWT"
    if (request.getCookies() != null) {
      for (jakarta.servlet.http.Cookie c : request.getCookies()) {
        if ("JWT".equals(c.getName())) {
          return c.getValue();
        }
      }
    }
    return null;
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    String method = request.getMethod();

    // Endpoints públicos: auth/login, refresh, registro, y flujo OAuth2
    if (path.equals("/api/auth") || path.equals("/api/auth/refresh")) return true;
    if (path.equals("/api/auth/user") && method.equalsIgnoreCase("POST")) return true;

    // OAuth2 (social login)
    if (path.equals("/login")) return true;
    if (path.startsWith("/oauth2/")) return true;
    if (path.startsWith("/login/oauth2/")) return true;
    if (path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/img/") || path.equals("/error")) return true;

    return false;
  }

}