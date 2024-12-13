package GHEBACKEND.GHEBACKEND.security.Utilisateur;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.utils.Utils;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
public class UtilisateurService implements UserDetailsService{
    
    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Utilisateur loadUserByUsername(String username) throws UsernameNotFoundException {

		return utilisateurRepository.findByUtiUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("Cet utilisateur n'existe pas")
            );
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
}
