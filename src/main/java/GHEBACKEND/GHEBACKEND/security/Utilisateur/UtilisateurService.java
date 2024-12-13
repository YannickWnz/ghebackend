package GHEBACKEND.GHEBACKEND.security.Utilisateur;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor
public class UtilisateurService implements UserDetailsService{
    
    private final UtilisateurRepository utilisateurRepository;

	@Override
	public Utilisateur loadUserByUsername(String username) throws UsernameNotFoundException {

		return utilisateurRepository.findByUtiUsername(username)
            .orElseThrow(
                () -> new RuntimeException("Cet utilisateur n'existe pas")
            );
	}

}
