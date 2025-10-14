package ar.edu.utn.frba.dds.configuracion;

import ar.edu.utn.frba.dds.provider.CustomAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

  @Bean
  public AuthenticationManager authManager(HttpSecurity http, CustomAuthProvider provider) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(provider)
        .build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/","/login", "/anonimo/**").permitAll()
            // Recursos estáticos
            .requestMatchers("/sso/**","/css/**", "/js/**", "/images/**", "/doc/**").permitAll()
            // Registro
            .requestMatchers("/registrar").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/contribuyente/**").hasRole("CONTRIBUYENTE")
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")    
            .permitAll()
            .defaultSuccessUrl("/home", true) 
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/?logout") 
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .permitAll()
        )
        .exceptionHandling(ex -> ex
            .authenticationEntryPoint((request, response, authException) ->
                response.sendRedirect("/?unauthorized")
            )
            .accessDeniedHandler((request, response, accessDeniedException) ->
                response.sendRedirect("/403")
            )
        );

    return http.build();
  }
}