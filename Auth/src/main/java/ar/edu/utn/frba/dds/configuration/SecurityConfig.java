package ar.edu.utn.frba.dds.configuration;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import ar.edu.utn.frba.dds.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ar.edu.utn.frba.dds.oauth.OAuth2SuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  private OAuth2SuccessHandler oAuth2SuccessHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
        .authorizeHttpRequests(auth -> {
          // públicos existentes
          auth.requestMatchers("/api/auth", "/api/auth/refresh").permitAll();
          auth.requestMatchers(POST, "/api/auth/user").permitAll();

          // NUEVO: endpoints para el flujo OAuth2
          auth.requestMatchers("/oauth2/**", "/login/oauth2/**", "/login").permitAll();

          // (opcional) estáticos/errores
          auth.requestMatchers("/css/**", "/js/**", "/img/**", "/error").permitAll();

          // todo lo demás, autenticado
          auth.anyRequest().authenticated();
        })
        // NUEVO: habilitar login con OAuth2
        // por ahora usamos defaultSuccessUrl; después enchufamos nuestro SuccessHandler
        .oauth2Login(oauth -> oauth
            .loginPage("/login")
            .successHandler(oAuth2SuccessHandler)
        )
        // (opcional recomendado) logout que borra cookie JWT si la usás
        .logout(logout -> logout
            .logoutUrl("/logout")
            .deleteCookies("JWT")
            .logoutSuccessUrl("/")
        )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}

