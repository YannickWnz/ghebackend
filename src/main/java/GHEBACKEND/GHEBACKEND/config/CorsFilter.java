// package GHEBACKEND.GHEBACKEND.config;


// import javax.servlet.Filter;
// import javax.servlet.FilterChain;
// import javax.servlet.FilterConfig;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.HttpServletResponse;
// import org.springframework.stereotype.Component;
// import java.io.IOException;

// @Component
// public class CorsFilter implements Filter {

//     @Override
//     public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
//             throws IOException, ServletException {
//         HttpServletResponse response = (HttpServletResponse) res;
//         response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//         response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//         response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//         chain.doFilter(req, res);
//     }

//     @Override
//     public void init(FilterConfig filterConfig) {}

//     @Override
//     public void destroy() {}
// }