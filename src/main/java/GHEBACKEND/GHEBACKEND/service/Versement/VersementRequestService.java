package GHEBACKEND.GHEBACKEND.service.Versement;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import GHEBACKEND.GHEBACKEND.model.Versement.Versement;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VersementRequestService  {

    private final VersementService versementService;
    private final RubriquePayerService rubriquePayerService;
    private final InscriptionService inscriptionService;

    public VersementResponse versement(VersementRequest request){
       Inscription inscription =  inscriptionService.getInscriptionById(request.getInscription().getInsCode());
       //Recherche des rubriques pour la classe à laquelle l'Etudiant s'est inscrit

       //Recupération des rubriques de la classe liée à l'inscription
        List<RubriqueModel> rubriques = Optional
            .ofNullable(inscription.getClasse().getRubrique())
            .orElseThrow(() -> new IllegalStateException("Aucune rubrique n'est liée à cette classe"));

        Double montant = request.getMontantVerse();
        Double montantTotal = montantTotal(rubriques);
        VersementResponse response = new VersementResponse();

        if(montant == null){
            throw new IllegalStateException("Aucun montant renseigné");
        }
        if(!Objects.equals(rubriques, null)){           
                //Si l'etudiant à déjà payé une rubrique
                List<RubriquePayer> payers = inscription.getRubliquesPayes();
    /*             
                        if(payers.size() > 0 ){
                            for (RubriquePayer payer : payers) {
            
                                if (!Objects.equals(payer.getRbpMontant(), payer.getRbpPrevu()) &&
                                    payer.getRbpMontant() < payer.getRbpPrevu()) 
                                {
                                    payer.setRbpMontant(payer.getRbpMontant() + request.getMontantVerse());
                                    rubriquePayerService.updateRubriquePayer(payer.getRbpCode(), payer);
                
                                    Versement versement = new Versement();
                                    versement.setInscription(inscription);
                                    versement.setVerMontant(request.getMontantVerse());
                                    versementService.createVersement(versement);
    
                                    response.setMessage("Succès");
                                    response.setDescription("Versement effectué avec succès");
                                }else{
    
                                    for (RubriqueModel rubrique : rubriques) {
                                        if(montant >= rubrique.getRubMontant()){
                        
                                            montant -= rubrique.getRubMontant();
                                            RubriquePayer payer1 = new RubriquePayer();
                                            payer1.setRbpMontant(rubrique.get);
                                            payer1.setRubrique(rubrique);
                                            payer1.setRbpDate(LocalDate.now());
                                            payer1.setInscription(inscription);
                                            payer1.setRbpPrevu(rubrique.getRubMontant());
                                            rubriquePayerService.createRubriquePayer(payer1);
                                            
                                            Versement versement = new Versement();
                                            versement.setInscription(inscription);
                                            versement.setVerMontant(request.getMontantVerse());
                                            versementService.createVersement(versement);
                                        }
                                        else throw new 
                                        IllegalStateException(
                                            String.format("Ce montant est inférieur au montant prévu pour ce paiement, vous devez disposer de plus de %s afin d'effectuer cette operation", 
                                            rubrique.getRubMontant() - montant)    
                                        );
                                    }
                                }
                            }
                        }else{
                            for (RubriqueModel rubrique : rubriques) {
                                if(montant >= rubrique.getRubMontant()){
                
                                    montant -= rubrique.getRubMontant();
                                    RubriquePayer payer1 = new RubriquePayer();
                                    payer1.setRbpMontant(montant);
                                    payer1.setRubrique(rubrique);
                                    payer1.setRbpDate(LocalDate.now());
                                    payer1.setInscription(inscription);
                                    payer1.setRbpPrevu(rubrique.getRubMontant());
                                    rubriquePayerService.createRubriquePayer(payer1);
                                    
                                    Versement versement = new Versement();
                                    versement.setInscription(inscription);
                                    versement.setVerMontant(request.getMontantVerse());
                                    versementService.createVersement(versement);
                                }
                                else throw new 
                                IllegalStateException(
                                    String.format("Ce montant est inférieur au montant prévu pour ce paiement, vous devez disposer de plus de %s afin d'effectuer cette operation", 
                                    rubrique.getRubMontant() - montant)    
                                );
                            }
                        }
                        */
        
                        
        }else{
            throw new 
            IllegalStateException(String.format("Aucune rubrique n'est disponible pour la salle %s", inscription.getClasse().getCla_lib())); 
        } 
        
       return response;
    }

    public Double montantTotal(List<RubriqueModel> rubriques){
        Double montantTotal = 0.0;
        for (RubriqueModel rubrique : rubriques) {
            montantTotal += rubrique.getRubMontant();
        }
        return montantTotal;
    }
}
