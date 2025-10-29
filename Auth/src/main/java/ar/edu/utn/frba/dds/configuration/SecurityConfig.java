package ar.edu.utn.frba.dds.configuration;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import ar.edu.utn.frba.dds.filter.JwtAuthenticationFilter;
import ar.edu.utn.frba.dds.oauth.OAuth2SuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final OAuth2SuccessHandler oAuth2SuccessHandler;

  public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                        OAuth2SuccessHandler oAuth2SuccessHandler) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.oAuth2SuccessHandler = oAuth2SuccessHandler;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/landing", "/index", "/health", "/error").permitAll()
            .requestMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico").permitAll()
            .requestMatchers("/api/auth", "/api/auth/refresh").permitAll()
            .requestMatchers(POST, "/api/auth/user").permitAll()
            .requestMatchers("/oauth2/**", "/oauth2/authorization/**", "/login/oauth2/**", "/login").permitAll()
            .anyRequest().authenticated()
        )
        .oauth2Login(oauth -> oauth
            .loginPage("/login")
            .successHandler(oAuth2SuccessHandler)
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .deleteCookies("JWT")
            .logoutSuccessUrl("/")
        )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
