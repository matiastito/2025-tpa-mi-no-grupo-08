package ar.edu.utn.frba.dds.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

import org.slf4j.Logger;               
import org.slf4j.LoggerFactory;          
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.utn.frba.dds.model.dto.auth.RolesPermisosDTO;
import ar.edu.utn.frba.dds.servicio.ExternalAuthService;

@Controller
public class SsoController {

  private static final Logger log = LoggerFactory.getLogger(SsoController.class); // <-- NUEVO

  private final ExternalAuthService externalAuthService;

  public SsoController(ExternalAuthService externalAuthService) {
    this.externalAuthService = externalAuthService;
  }

  @GetMapping("/sso/callback")
  public String ssoCallback(@RequestParam("token") String token, HttpServletRequest request) {
    String safe = token == null ? "(null)" : token.substring(0, Math.min(10, token.length()));
    log.info("👉 [SsoController] Callback recibido con token: {}...", safe);

    // ✅ Forzar siempre CONTRIBUYENTE en el flujo SSO
    String roleName = "ROLE_CONTRIBUYENTE";
    String username = "usuario"; // opcional: podés sustituirlo si luego querés traerlo del token

    var auth = new UsernamePasswordAuthenticationToken(
        username,
        null,
        List.of(new SimpleGrantedAuthority(roleName))
    );

    // Persistir en SecurityContext + sesión HTTP
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(auth);
    SecurityContextHolder.setContext(context);
    request.getSession(true).setAttribute(
        HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context
    );

    // (opcional) guardar el JWT por si lo necesitás para otras llamadas
    request.getSession(true).setAttribute("JWT_TOKEN", token);

    return "redirect:/home";
  }

}

