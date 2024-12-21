package GHEBACKEND.GHEBACKEND.service.Versement.Encaissement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.HistoriqueRubriqueInscription;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import GHEBACKEND.GHEBACKEND.model.Versement.Versement;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.RubriqueService;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import GHEBACKEND.GHEBACKEND.service.Inscription.HistoriqueRubriqueInscription.HistoriqueRubriqueInscriptionService;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.EtudiantService;
import GHEBACKEND.GHEBACKEND.service.Versement.VersementRequest;
import GHEBACKEND.GHEBACKEND.service.Versement.VersementRequestService;
import GHEBACKEND.GHEBACKEND.service.Versement.VersementResponse;
import GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayer.RubriquePayerService;
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
    private final HistoriqueRubriqueInscriptionService historiqueRubriqueInscriptionService;

    public EncaissementResponse encaissement(EncaissementRequest request){
        
        if(Objects.equals(request.getMontant(), null)) throw new EntityNotFoundException("Attention le montant est obligatoire");
        if(Objects.equals(request.getInscription(), null)) throw new RuntimeException("Aucun étudiant envoyé pour cette requête...");

        request.setInscription(inscriptionService.getInscriptionById(request.getInscription().getInsCode()));
        final EtudiantModel etudiant = etudiantService.getEtudiantByEtuCode(request.getInscription().getEtudiant().getEtdCode());
        
        //TODO: afficher la liste des inscriptions qui ne sont pas encore soldées
        List<Inscription> inscriptionsEtudiant = inscriptionService.getInscriptionByInsSoldeAndEtudiant(false,etudiant);
       
        

        //TODO: Récuperation de l'inscription dont l'année est en cours
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
                    montantRestant = response.getMontantRestant();
                    montantVerser = response.getMontantVerse();
                   
                    if(!montantVerser.equals(0.0))
                        this.versementRequestService.verser(
                            inscription,
                            response.getMontantTotalVerse()
                        );
                }
            }
            return EncaissementResponse.builder()
                .message(response.getMessage())
                .description(response.getDescription())
                .montantVerse(montantVerser)
                .montantRestant(montantRestant)
                .build();
        }
        else throw new EntityNotFoundException("Toutes les rubriques sont déjà régularisé");
    }

  
}

