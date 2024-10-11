// package GHEBACKEND.GHEBACKEND.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**") // Allow all endpoints
//                 .allowedOrigins("http://localhost:3000") // Your frontend URL
//                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
//                 .allowCredentials(true); // Allow credentials if needed
//     }
// }

// // import org.springframework.http.HttpHeaders;
// // import java.util.Arrays;

// // import org.apache.catalina.filters.CorsFilter;
// // import org.springframework.boot.web.servlet.FilterRegistrationBean;
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.http.HttpMethod;
// // import org.springframework.web.cors.CorsConfiguration;
// // import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// // import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// // @Configuration
// // @EnableWebMvc
// // public class WebConfig {

// //     @Bean
// //     public FilterRegistrationBean corsFilter() {

// //         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
// //         CorsConfiguration config = new CorsConfiguration();
// //         config.setAllowCredentials(true);

// //         config.addAllowedOrigin("http://localhost:3000");
// //         config.setAllowedHeaders(Arrays.asList(
// //             HttpHeaders.AUTHORIZATION,
// //             HttpHeaders.CONTENT_TYPE,
// //             HttpHeaders.ACCEPT
// //         ));
// //         config.setAllowedMethods(Arrays.asList(
// //             HttpMethod.GET.name(),
// //             HttpMethod.POST.name(),
// //             HttpMethod.PUT.name(),
// //             HttpMethod.DELETE.name()
// //         ));
// //         config.setMaxAge(3600L);
// //         source.registerCorsConfiguration("/**", config);
// //         FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter());
// //         bean.setOrder(-102);
// //         return bean;

// //     }

// // }
