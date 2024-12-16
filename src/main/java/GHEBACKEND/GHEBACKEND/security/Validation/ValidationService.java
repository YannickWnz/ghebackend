package GHEBACKEND.GHEBACKEND.security.Validation;

import java.time.Instant;
import java.util.Random;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.security.Utilisateur.Utilisateur;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final ValidationRepository validationRepository;

    public void creer(Utilisateur utilisateur){
        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);
        
        Instant creation = Instant.now();
        validation.setCreation(creation);
        validation.setExpiration(creation.plus(10, MINUTES));
        validation.setCode(String.format("%06d",(new Random()).nextInt(999999)));
        this.validationRepository.save(validation);
    }

    public Validation getValidationByCode(String code){
        return this.validationRepository.findByCode(code)
            .orElseThrow(
                () -> new RuntimeException("Ce code n'existe pas")
            );
    }
}
