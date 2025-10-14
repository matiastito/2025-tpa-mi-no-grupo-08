package ar.edu.utn.frba.dds.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

  @Value("${app.jwt.secret}")
  private String secret;

  @Value("${app.jwt.access-ttl-seconds:86400}")   // 24 hs por defecto
  private long accessTtlSeconds;

  @Value("${app.jwt.refresh-ttl-seconds:604800}") // 7 días por defecto
  private long refreshTtlSeconds;

  /**
   * Genera un Access Token con claims de rol y nombre visible.
   */
  public String generarAccessToken(String username, String role, String name) {
    Instant now = Instant.now();
    return Jwts.builder()
        .setSubject(username)
        .claim("role", role)
        .claim("name", name)
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plusSeconds(accessTtlSeconds)))
        .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
        .compact();
  }

  /**
   * Compatibilidad: si algún código viejo solo pasa username,
   * asumimos ROLE_CONTRIBUYENTE y name = username.
   */
  public String generarAccessToken(String username) {
    return generarAccessToken(username, "ROLE_CONTRIBUYENTE", username);
  }

  /**
   * Genera un Refresh Token con claim type=refresh.
   */
  public String generarRefreshToken(String username) {
    Instant now = Instant.now();
    return Jwts.builder()
        .setSubject(username)
        .claim("type", "refresh")
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plusSeconds(refreshTtlSeconds)))
        .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
        .compact();
  }

  /**
   * Parsea y valida la firma/expiración del token, devolviendo el JWS para leer claims.
   */
  public Jws<Claims> parse(String token) throws JwtException {
    return Jwts.parserBuilder()
        .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
        .build()
        .parseClaimsJws(token);
  }

  /**
   * Compatibilidad: devuelve el 'sub' (username) si el token es válido.
   */
  public String validarToken(String token) {
    return parse(token).getBody().getSubject();
  }
}
