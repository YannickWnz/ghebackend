package GHEBACKEND.GHEBACKEND.service.Versement;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import GHEBACKEND.GHEBACKEND.repository.Versement.RubriquePayerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RubriquePayerService {

    private final RubriquePayerRepository rubriquePayerRepository;

    public RubriquePayer createRubriquePayer(RubriquePayer rubriquePayer){
        return rubriquePayerRepository.save(rubriquePayer);
    }

    public RubriquePayer getRubriquePayerByCode(@NonNull String code){
        return rubriquePayerRepository.findById(code)
            .orElseThrow(
                () -> new IllegalStateException(String.format("Cette rubrique de code %s n'existe pas", code)));
    }

    public List<RubriquePayer> getAllRubriquePayer(){
        return rubriquePayerRepository.findAll();
    }

    public List<RubriquePayer> getRubriquePayerByInscription(Inscription inscription){
        return rubriquePayerRepository.findByInscriptionOrderByRbpCodeAsc(inscription)
                .orElseThrow(() -> new IllegalStateException("Aucune rubrique ne correspond à cette inscription"));
    }
}
