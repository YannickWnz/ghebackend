package GHEBACKEND.GHEBACKEND.security.Authentication;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.security.Authentication.Register.AuthenticationRequest;
import GHEBACKEND.GHEBACKEND.security.Authentication.Register.RegisterRequest;
import GHEBACKEND.GHEBACKEND.security.Authentication.Register.RegisterResponse;
import GHEBACKEND.GHEBACKEND.security.Config.JwtService;
import GHEBACKEND.GHEBACKEND.security.Permission.Permission;
import GHEBACKEND.GHEBACKEND.security.Permission.PermissionService;
import GHEBACKEND.GHEBACKEND.security.Role.Role;
import GHEBACKEND.GHEBACKEND.security.Role.RoleService;
import GHEBACKEND.GHEBACKEND.security.Utilisateur.Utilisateur;
import GHEBACKEND.GHEBACKEND.security.Utilisateur.UtilisateurService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurService utilisateurService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PermissionService permissionService;

    public Utilisateur inscrire(AuthenticationRequest authenticationRequest){
        Utilisateur utilisateur = Utilisateur.builder()
                    .role(roleService.getRoleById(2))
                    .utiEmail(authenticationRequest.getEmail())
                    .utiPassword(authenticationRequest.getPassword())
                    .utiNom(authenticationRequest.getNom())
                    .utiPrenom(authenticationRequest.getPrenom())
                    .utiUsername(authenticationRequest.getUsername())
                    .build();
        return this.utilisateurService.createUtilisateur(utilisateur);
    }

    public RegisterResponse connexion(RegisterRequest registerRequest){
        Utilisateur utilisateur = this.utilisateurService.loadUserByUsername(registerRequest.getUsername());
        Role role = roleService.getRoleById(utilisateur.getRole().getRolId());
        role.setPermissions(getSetPermissionAuthorities(utilisateur.getRole()));
        utilisateur.setRole(role);
        RegisterResponse registerResponse = new RegisterResponse();
        if (utilisateur.isUtiActif()){
            /* List<GrantedAuthority> authorities = getAuthorities(utilisateur.getRole()); */
            final Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    registerRequest.getUsername(), registerRequest.getPassword(), utilisateur.getAuthorities())
            );
            if (authentication.isAuthenticated()) {
                Map<String, String> token = this.jwtService.generateJwt(registerRequest.getUsername());
                registerResponse = RegisterResponse.builder().token(token.get("bearer")).build();
            }else throw new RuntimeException("Non authorisé");
        }else 
            throw new RuntimeException("Vous ne disposer pas des droits nécessaires pour vous authentifiés, ne compte est inactif");
        return registerResponse;
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
