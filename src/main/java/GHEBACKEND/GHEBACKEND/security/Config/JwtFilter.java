package GHEBACKEND.GHEBACKEND.security.Config;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import GHEBACKEND.GHEBACKEND.security.Authentication.AuthenticationService;
import GHEBACKEND.GHEBACKEND.security.Jwt.Jwt;
import GHEBACKEND.GHEBACKEND.security.Permission.Permission;
import GHEBACKEND.GHEBACKEND.security.Permission.PermissionService;
import GHEBACKEND.GHEBACKEND.security.Role.Role;
import GHEBACKEND.GHEBACKEND.security.Role.RoleService;
import GHEBACKEND.GHEBACKEND.security.Utilisateur.Utilisateur;
import GHEBACKEND.GHEBACKEND.security.Utilisateur.UtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UtilisateurService utilisateurService;
    private final PermissionService permissionService;
    private final HandlerExceptionResolver handlerExceptionResolver;
	@Override
	protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
			throws ServletException, IOException {
		final String token;
        String username = null;
        boolean isTokenExpired = true;
        /* Jwt jwt = null; */
        final String authorization = request.getHeader("Authorization");

        try {

            if (authorization != null && authorization.startsWith("Bearer ")) {
                token = authorization.substring(7);
                /* jwt = jwtService.tokenByValue(token); */
                isTokenExpired = jwtService.isTokenExpired(token);
                username = jwtService.extractUsername(token);
            }
            
            if (!isTokenExpired && 
                username != null && 
                /* jwt.getUtilisateur().getUsername().equals(username) && */
                SecurityContextHolder.getContext().getAuthentication() == null) {
                
                    Utilisateur utilisateur = utilisateurService.loadUserByUsername(username);
                    utilisateur = this.utilisateurService.updateAuthorities(utilisateur);
                    var authorities = utilisateur.getAuthorities();
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                            utilisateur,
                            null,
                            utilisateur.getAuthorities()
                        );
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (Exception e) {
            logger.info(e);
            handlerExceptionResolver.resolveException(request, response, authorization, e);
        }

        filterChain.doFilter(request, response);
	}

     public Set<Permission> getSetPermissionAuthorities(Role role){
        final List<Permission> permissions = this.permissionService.getPermissions(role);
        Set<Permission> permissionSet = new HashSet<>();
        for (Permission permission : permissions) {
            permissionSet.add(permission);
        }
        return permissionSet;
    }
}
