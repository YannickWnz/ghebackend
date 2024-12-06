package GHEBACKEND.GHEBACKEND.service.Versement;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VersementRequestService  {

    private final VersementService versementService;
    private final RubriquePayerService rubriquePayerService;
    private final InscriptionService inscriptionService;

    public VersementResponse verser(VersementRequest request){
       Inscription inscription =  inscriptionService.getInscriptionById(request.getInscription().getInsCode());
       //Recherche des rubriques pour la classe à laquelle l'Etudiant s'est inscrit
       List<RubriqueModel> rubriques = Optional
            .ofNullable(inscription.getClasse().getRubrique())
            .orElseThrow(() -> new IllegalStateException("Aucune rubrique n'est liée à cette classe"));
        
        Double montant = request.getMontantVerse();
        for (RubriqueModel rubrique : rubriques) {
            try {
                RubriquePayer payer = rubriquePayerService.getRubriquePayer(inscription, rubrique);
                if(!Objects.equals(payer.getRbpMontant(), payer.getRbpPrevu())){
                    if(montant <= payer.getRbpMontantRestant()){
                        payer.setRbpMontant(payer.getRbpMontant() + montant);
                        montant = montant - rubrique.getRubMontant();
                    }
                }
            } catch (Exception e) {
                
            }
        }


       return new VersementResponse("Succès","Versement effectué avec succès");
    }
}
