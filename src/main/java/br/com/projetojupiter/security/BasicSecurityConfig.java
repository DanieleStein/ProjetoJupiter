package br.com.projetojupiter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    //public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
    //    return authenticationConfiguration.getAuthenticationManager();
    //}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .cors();

        http.authorizeHttpRequests(auth -> auth
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/usuario/login").permitAll()
                .antMatchers("/usuario/register").permitAll()
                .antMatchers("/criador/register").permitAll()
                .antMatchers("/criador/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()).httpBasic();

        return http.build();
    }


    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // other public endpoints of your API may be appended to this array
            "/usuario/login",
            "/usuario/register",
            "/curso",
            "/conteudos",
            "/cartao",
            "/pedido",
            "/usuario",
            "/criador/login",
            "/criador/register"
    };
}
