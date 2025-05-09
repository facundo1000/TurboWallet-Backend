package org.alkemy.alkywallet.config;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.config.filters.JwtTokenValidator;
import org.alkemy.alkywallet.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class
 * <br>
 * Autor:Squad2
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    /**
     * Security filter chain configuration
     * @param http
     * @return HttpSecurity object
     * @throws Exception
     */
    //TODO: mejorar los permisos de seguridad

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth ->

                        auth.requestMatchers("/h2", "/h2/**", "/api/v1/auth/**","/openapi/**").permitAll()
                                .requestMatchers("/api/v1/cuentas/**","/api/v1/tarjetas/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/usuarios/activos","/api/v1/tarjetas/activas").hasRole("USER")
                                .anyRequest()
                                .authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable) //Desactiva la protección CSRF (Cross-Site Request Forgery)
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new JwtTokenValidator(jwtUtils), UsernamePasswordAuthenticationFilter.class)
                //no se usará sesión HTTP
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //permite el uso de iframes. Necesario para el uso de H2.
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    /**
     * Funcion que permite utilizar un encriptador de contrasenias
     *
     * @return PasswordEncoder object
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }
}
