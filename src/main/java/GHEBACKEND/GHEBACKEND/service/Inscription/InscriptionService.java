package GHEBACKEND.GHEBACKEND.service.Inscription;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.repository.Inscription.InscriptionRepository;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public Inscription createInscription(Inscription inscription){
        return inscriptionRepository.save(inscription);
    }

    public List<Inscription> getAllInscription(){
        return inscriptionRepository.findAll();
    }

    public Optional<List<Inscription>> getInscriptionByNiveauValidation(@NonNull int insNiveauValidation){
        return Optional.ofNullable(inscriptionRepository.findByInsNiveauValidation(insNiveauValidation))
                .orElseThrow(
                    () ->  new IllegalStateException("Aucune inscription à ce niveau...")
                );
    }

    public void deleteInscriptionById(@NonNull Integer code){
        boolean exists = inscriptionRepository.existsById(code);
        if(!exists){
            throw new IllegalStateException("Cette inscription n'existe pas");
        }
        inscriptionRepository.deleteById(code);
    }

    public Integer generateInscriptionCode(){
        return 24110001;
    }
}
