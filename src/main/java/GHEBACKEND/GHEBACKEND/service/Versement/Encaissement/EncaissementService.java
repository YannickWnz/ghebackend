package GHEBACKEND.GHEBACKEND.service.Versement.Encaissement;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import GHEBACKEND.GHEBACKEND.model.Versement.Versement;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.RubriqueService;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.EtudiantService;
import GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayerService;
import GHEBACKEND.GHEBACKEND.service.Versement.VersementRequest;
import GHEBACKEND.GHEBACKEND.service.Versement.VersementRequestService;
import GHEBACKEND.GHEBACKEND.service.Versement.VersementResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncaissementService {

    private final EtudiantService etudiantService;
    private final RubriquePayerService rubriquePayerService;
    private final RubriqueService rubriqueService;
    private final InscriptionService inscriptionService;
    private final VersementRequestService versementRequestService;

    public EncaissementResponse encaissement(EncaissementRequest request){
        
        EtudiantModel etudiant = etudiantService.getEtudiantByEtuCode(request.getEtudiant().getEtdCode());
        List<Inscription> inscriptionsEtudiant = inscriptionService.getInscriptionByEtudiant(etudiant);
        VersementResponse response = null;
        if(request.getMontant() < 0 && request.getMontant() == null) throw new RuntimeException("Aucun montant pour effectuer cet encaissement");
        Double montantRestant = request.getMontant(), montantVerser = null;

        if (inscriptionsEtudiant.size() > 0) {
            for (Inscription inscription : inscriptionsEtudiant) {
                if (montantRestant > 0) {
                    VersementRequest versementRequest = VersementRequest.builder()
                        .date(request.getDate())
                        .inscription(inscription)
                        .montantVerse(montantRestant)
                        .build();
                    response =  versementRequestService.versement(versementRequest);
                    if(response.getMontantRestant() < 0)
                        montantVerser = montantRestant;
                    else
                        montantVerser = montantRestant - response.getMontantRestant();
                    montantRestant = response.getMontantRestant();

                    if(!montantVerser.equals(0.0))
                        this.versementRequestService.verser(
                            inscription,
                            montantVerser
                        );
                }
            }
            return EncaissementResponse.builder()
                .message(response.getMessage())
                .description(response.getDescription())
                .build();
        }
        else throw new EntityNotFoundException("Aucune inscription liée à cet etudiant");
    }
}
