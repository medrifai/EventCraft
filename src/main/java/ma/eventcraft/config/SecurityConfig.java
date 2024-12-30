package ma.eventcraft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/signup", "/css/**", "/js/**", "/my-ticket-page").permitAll() // Allow unauthenticated access to /my-ticket-page
                .anyRequest().authenticated() // Require authentication for other pages
            )
            .formLogin(form -> form
                .loginPage("/login") // Set the correct login page URL
                .permitAll() // Allow everyone to access the login page
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}