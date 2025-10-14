package ar.edu.utn.frba.dds.filter;

import ar.edu.utn.frba.dds.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JWTUtil jwtUtil;

  public JwtAuthenticationFilter(JWTUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws ServletException, IOException {

    String token = readJwtCookie(request);

    if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      try {
        Jws<Claims> jws = jwtUtil.parse(token);
        String username = jws.getBody().getSubject();
        String role = jws.getBody().get("role", String.class); // viene del claim

        var auth = new UsernamePasswordAuthenticationToken(
            username, null, List.of(new SimpleGrantedAuthority(role)));
        SecurityContextHolder.getContext().setAuthentication(auth);

      } catch (JwtException ex) {
        // token inválido/expirado → seguimos anónimos
      }
    }

    chain.doFilter(request, response);
  }

  private String readJwtCookie(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null) return null;
    for (Cookie c : cookies) {
      if ("JWT".equals(c.getName())) {
        return c.getValue();
      }
    }
    return null;
  }
}

