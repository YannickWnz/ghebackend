package GHEBACKEND.GHEBACKEND.security.Utilisateur;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;

import GHEBACKEND.GHEBACKEND.security.Permission.Permission;
import GHEBACKEND.GHEBACKEND.security.Permission.PermissionService;
import GHEBACKEND.GHEBACKEND.security.Role.Role;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
public class UtilisateurService implements UserDetailsService{
    
    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PermissionService permissionService;

	@Override
	public Utilisateur loadUserByUsername(String username) throws UsernameNotFoundException {

		return utilisateurRepository.findByUtiUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("Cet utilisateur n'existe pas")
            ); 
	}

    public Utilisateur updateAuthorities(Utilisateur utilisateur){
        utilisateur.getRole().setPermissions(this.getSetPermissionAuthorities(utilisateur.getRole()));
        return utilisateur;
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur){
        boolean existsUtilisateur = this.utilisateurRepository.existsByUtiUsername(utilisateur.getUsername());
        
        if (!existsUtilisateur) {
            throw new IllegalStateException("Cet utilisateur existe déjà");
        }
        utilisateur.setUtiCode(generateCodeUtilisateur());
        utilisateur.setUtiActif(false);
        utilisateur.setUtiPassword(this.bCryptPasswordEncoder.encode(utilisateur.getPassword()));
      return this.utilisateurRepository.save(utilisateur);
    }

    private String generateCodeUtilisateur(){
        Optional<String> maximumCodeOptional = this.utilisateurRepository.findMaximumUtilisateur();
        String code;
        if (maximumCodeOptional.isPresent()) {
            code = maximumCodeOptional.get().substring(6);
            code = Utils.formatFoorString(Utils.incrementValue(code).toString());
        }else
            code = "00001";
        return Utils.formatterTime(LocalDateTime.now()).concat(code);
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
