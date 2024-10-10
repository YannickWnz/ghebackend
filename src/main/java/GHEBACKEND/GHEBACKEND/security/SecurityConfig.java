package GHEBACKEND.GHEBACKEND.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import GHEBACKEND.GHEBACKEND.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    // @Bean
    // public JwtAuthFilter jwtAuthFilter() {
    //     return new JwtAuthFilter();
    // }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Use custom CORS configuration
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection (enable if using forms)
            // .authorizeHttpRequests(auth -> auth
            //     .requestMatchers("/register", "/login").permitAll() // Allow access to register and login endpoints
            //     // .requestMatchers("/api/getStudent").authenticated()  // Require authentication for this endpoint
            //     .anyRequest().authenticated() // All other requests require authentication
            // )
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            )
            // .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //        public void addCorsMappings(CorsRegistry registry) {
    //             registry.addMapping("/**")
    //                 .allowedOrigins("http://localhost:3000")
    //                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
    //                 .allowedHeaders("*")
    //                 .allowCredentials(true);
    //         }
    //     };
    // }

     @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true); // Allow credentials if needed
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
