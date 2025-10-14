package ar.edu.utn.frba.dds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

  @GetMapping("/login")
  @ResponseBody
  public String login() {
    return """
      <html><head><meta charset='utf-8'><title>Login</title></head>
      <body style="font-family: sans-serif; margin:40px">
        <h1>Ingresar</h1>
        <p>Elegí un método de acceso:</p>
        <p><a href="/oauth2/authorization/google"
              style="padding:10px 16px;border:1px solid #999;text-decoration:none;display:inline-block">
              Continuar con Google
           </a>
        </p>
      </body></html>
    """;
  }
}
