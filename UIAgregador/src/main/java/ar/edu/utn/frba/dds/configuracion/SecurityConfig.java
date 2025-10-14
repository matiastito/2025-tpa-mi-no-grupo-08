package ar.edu.utn.frba.dds.configuracion;

import ar.edu.utn.frba.dds.provider.CustomAuthProvider;
import ar.edu.utn.frba.dds.security.CustomAuthSuccessHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                CustomAuthProvider customAuthProvider,
                                                CustomAuthSuccessHandler successHandler) throws Exception {
    http
        .authenticationProvider(customAuthProvider)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/login", "/anonimo/**").permitAll()
            .requestMatchers("/sso/**", "/css/**", "/js/**", "/images/**", "/doc/**").permitAll()
            .requestMatchers("/registrar").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .successHandler(successHandler)
            .failureUrl("/login?error")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .clearAuthentication(true)
            .permitAll()
        )
        .exceptionHandling(ex -> ex
            .authenticationEntryPoint((req, res, ex1) -> res.sendRedirect("/?unauthorized"))
            .accessDeniedHandler((req, res, ex2) -> res.sendRedirect("/403"))
        );

    return http.build();
    }

}