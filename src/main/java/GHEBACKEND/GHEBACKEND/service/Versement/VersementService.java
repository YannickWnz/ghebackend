package GHEBACKEND.GHEBACKEND.service.Versement;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.Versement.Versement;
import GHEBACKEND.GHEBACKEND.repository.Versement.VersementRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VersementService {

    private final VersementRepository versementRepository;

    public Versement createVersement(Versement versement){
        return versementRepository.save(versement);
    }

    public List<Versement> getAllVersement(){
        return versementRepository.findAll();
    }

    public List<Versement> getVersementByInscription(Inscription inscription){
        return versementRepository.findByInscriptionOrderByVerDateAsc(inscription)
                .orElseThrow(
                    () -> new IllegalStateException(String.format("Aucun versement pour l'inscription de numero %s", 
                    inscription.getInsCode())));
    }
}
