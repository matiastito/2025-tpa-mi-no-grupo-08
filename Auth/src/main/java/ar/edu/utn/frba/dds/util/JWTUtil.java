package ar.edu.utn.frba.dds.util;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.security.Keys.secretKeyFor;
import static java.lang.System.currentTimeMillis;

import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.Date;

public class JWTUtil {
  private static final Key key = secretKeyFor(HS256);

  private static final long ACCESS_TOKEN_VALIDITY = 15 * 60 * 1000; // 15 min
  private static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000; // 7 días

  public static String generarAccessToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuer("gestion-alumnos-server")
        .setExpiration(new Date(currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
        .signWith(key)
        .compact();
  }

  public static String generarRefreshToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuer("gestion-alumnos-server")
        .setExpiration(new Date(currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
        .claim("type", "refresh") // diferenciamos refresh del access
        .signWith(key)
        .compact();
  }

  public static String validarToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public static Key getKey() {
    return key;
  }
}
