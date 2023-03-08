package com.example.demo.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private static final String[] AUTH_WHITELIST = {
            "/api/auth/**",
    };

     private static final String[] LOGGEDIN_WHITELIST = {
            "/dashboard/**",
            "/profile/**",
    };



    private static final String[] AUTH_BLACKLIST = {
        "/api/admin/**"
    };

    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST)
                .permitAll()
                .and()
//                .authorizeHttpRequests()
//                .requestMatchers(AUTH_BLACKLIST)
//                .hasAuthority("ADMIN")
//                .anyRequest()
//                .authenticated()
//                .and()

                .authorizeHttpRequests()
                .requestMatchers(LOGGEDIN_WHITELIST)
                .hasAnyAuthority("USER", "ADMIN")
                .anyRequest()
                .authenticated()
                .and()

                .formLogin()
                .and()
                .build();


    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
