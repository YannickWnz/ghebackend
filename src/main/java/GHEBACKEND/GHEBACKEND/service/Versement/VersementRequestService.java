package GHEBACKEND.GHEBACKEND.service.Versement;

import java.time.LocalDate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.HistoriqueRubriqueInscription;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import GHEBACKEND.GHEBACKEND.model.Versement.Versement;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.RubriqueRepo;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import GHEBACKEND.GHEBACKEND.service.Inscription.HistoriqueRubriqueInscription.HistoriqueRubriqueInscriptionService;
import GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayer.RubriquePayerService;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VersementRequestService  {

    private final VersementService versementService;
    private final RubriquePayerService rubriquePayerService;
    private final InscriptionService inscriptionService;
    private final RubriqueRepo rubriqueRepository;
    private final HistoriqueRubriqueInscriptionService historiqueRubriqueInscriptionService;

    public VersementResponse versement(VersementRequest request){
       Inscription inscription =  inscriptionService.getInscriptionById(request.getInscription().getInsCode());

       //Recupération des rubriques de la classe liée à l'inscription
        List<RubriqueModel> rubriques = Optional
            .ofNullable(inscription.getClasse().getRubrique())
            .orElseThrow(() -> new IllegalStateException("Aucune rubrique n'est liée à cette classe"));

        Double montant = request.getMontantVerse();
        VersementResponse response = new VersementResponse();

        if(montant == null){
            throw new IllegalStateException("Aucun montant renseigné");
        }
        if(rubriques.size() > 0){           
                List<RubriqueModel> rubriqueModels = rubriqueRepository.findByClasseOrderByRubFraisUniqueDescRubOrdreAsc(
                    inscription.getClasse());
                //TODO: Récupérer les rubriques dans la table historique
                List<HistoriqueRubriqueInscription> historiqueRubriqueInscriptions = historiqueRubriqueInscriptionService
                    .getHistoriqueRubriqueInscriptionByInscription(inscription);

                //Récuperations de toutes les rubriques obligatoires
                List<RubriqueModel> rubriqueObligatoire = rubriqueRepository
                .findByClasseAndRubFraisUniqueTrueOrderByRubFraisUniqueAscRubOrdreAsc(inscription.getClasse());
                //On fait la somme des montants obligatoires
                 Double montantAverserObligatoire = sommeMontantRubrique(rubriqueObligatoire); 
                //Montant à verser
                Double montantRestant = request.getMontantVerse(),montantVerse = 0.0, montantTotalVerse = 0.0 ;
                boolean flag = false;

                List<RubriquePayer> rubriquePayers = inscription.getRubliquesPayes();
               
                //Vérifier historique de paiement pour cette inscription
                if (rubriquePayers.size() > 0) {

                    final Double montantTotalRubriquePayer = 
                    Objects.equals(rubriquePayerService.getSumRubMontantByInscription(inscription),null) 
                        ? 0.0 
                        : rubriquePayerService.getSumRubMontantByInscription(inscription);
                    final Double montantTotalHistorique = historiqueRubriqueInscriptionService.getSumHisMontantByInscription(inscription);
                   

                    if (montantTotalRubriquePayer < montantTotalHistorique) {
                        
                        for (HistoriqueRubriqueInscription historiqueRubriqueInscription : historiqueRubriqueInscriptions) {
                            if(montantRestant > 0){
                                
                                RubriquePayer rubriquePayer = rubriquePayerService.getRubriquePayerByRubriqueAndInscription(
                                        historiqueRubriqueInscription.getRubrique(),
                                        inscription);
                                        RubriquePayer rubriquePayer2;
                                
                                    if(!Objects.equals(rubriquePayer, null)){

                                        if ( rubriquePayer.getRbpMontantRestant() > 0) {
                                            Double montantAvantEncaissement = rubriquePayer.getRbpMontant();
                                            rubriquePayer2 = miseAjourEncaissement(rubriquePayer, montantRestant);
                                            montantVerse = rubriquePayer2.getRbpMontant() - montantAvantEncaissement;
                                            montantRestant = montantRestant - montantVerse;
                                            
                                            flag = true;
                                        }
                                    }else{
                                        rubriquePayer2 =  nouvelEncaissement(inscription,historiqueRubriqueInscription,montantRestant);
                                        montantVerse = montantVerse + rubriquePayer2.getRbpMontant();
                                        montantRestant = montantRestant - montantVerse;
                                        flag = true;
                                        
                                    }
                            }

                        }

                        inscriptionService.updateInsSoldInscription(inscription.getInsCode(), inscription);
                        response.setMessage("Succès");        
                        response.setDescription("Versement effectué avec succès");
                        response.setMontantRestant(montantRestant); 
                        response.setMontantVerse(montantVerse); 
                        
                    }else{
                        response.setMessage("Information");        
                        response.setDescription("Toutes les rubriques sont déjà soldées");
                        response.setMontantVerse(montantVerse);
                        response.setMontantRestant(montantRestant);     
                    }

                }else{
                    //Premier versement

                    if(request.getMontantVerse() >= montantAverserObligatoire){

                        for (RubriqueModel rubriqueModel : rubriqueModels) {
                            if(montantRestant > 0){
                                RubriquePayer rubriquePayer2 ;
                                    if(montantRestant >= rubriqueModel.getRubMontant() &&
                                       rubriqueModel.getRubFraisUnique()){
                                        rubriquePayer2 =  nouvelEncaissement(inscription, rubriqueModel,rubriqueModel.getRubMontant());
                                    }
                                    else{
                                        rubriquePayer2 = nouvelEncaissement(inscription, rubriqueModel, montantRestant);
                                    }
                                    montantVerse = montantVerse + rubriquePayer2.getRbpMontant();
                                    montantRestant = montantRestant - rubriquePayer2.getRbpMontant();
                                    montantTotalVerse = montantVerse;
                            }
                        }
                        
                        
                        
                        response.setDescription("Versement effectué avec succès");
                        response.setMessage("Succès");
                        response.setMontantRestant(montantRestant);
                        response.setMontantVerse(montantVerse);
                        
                    }else{
                        throw new IllegalStateException(String.format(
                            "Le montant restant %s FCFA ne permet pas d'effectuer cette operation, il faut atteindre %s FCFA",
                             montantRestant,montantAverserObligatoire));
                    }
                }
        }else{
            throw new 
            IllegalStateException(
                String.format(
                    "Aucune rubrique n'est disponible pour la classe %s",
                    inscription.getClasse().getCla_lib())); 
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

    public Versement verser(Inscription inscription,Double montant){
        Versement versement = new Versement();
        versement.setInscription(inscription);
        versement.setVerDate(LocalDate.now());
        versement.setVerMontant(montant);
        return versementService.createVersement(versement);
    }

    public RubriquePayer nouvelEncaissement(
        Inscription inscription,
        RubriqueModel rubriqueModel,
        Double montantVerse){
                            
        RubriquePayer rubriquePayer = new RubriquePayer();
        rubriquePayer.setInscription(inscription);
        rubriquePayer.setRubrique(rubriqueModel);
        rubriquePayer.setRbpPrevu(rubriqueModel.getRubMontant());

        if(montantVerse > rubriqueModel.getRubMontant()){
            rubriquePayer.setRbpMontant(rubriqueModel.getRubMontant());
            rubriquePayer.setRbpMontantRestant(0.0);
        }else{
            rubriquePayer.setRbpMontant(montantVerse);
            rubriquePayer.setRbpMontantRestant(rubriquePayer.getRbpPrevu() - montantVerse);
        }
        rubriquePayer.setRbpDate(LocalDate.now());
        return rubriquePayerService.createRubriquePayer(rubriquePayer);
    }


    public RubriquePayer nouvelEncaissement(
        Inscription inscription,
        HistoriqueRubriqueInscription historiqueRubriqueInscription,
        Double montantVerse){
                            
        RubriquePayer rubriquePayer = new RubriquePayer();
        rubriquePayer.setInscription(inscription);
        rubriquePayer.setRubrique(historiqueRubriqueInscription.getRubrique());
        rubriquePayer.setRbpPrevu(historiqueRubriqueInscription.getHisMontantPrevu());

        if(montantVerse > historiqueRubriqueInscription.getHisMontantPrevu()){
            rubriquePayer.setRbpMontant(historiqueRubriqueInscription.getHisMontantPrevu());
            rubriquePayer.setRbpMontantRestant(0.0);
        }else{
            rubriquePayer.setRbpMontant(montantVerse);
            rubriquePayer.setRbpMontantRestant(rubriquePayer.getRbpPrevu() - montantVerse);
        }
        rubriquePayer.setRbpDate(LocalDate.now());
        return rubriquePayerService.createRubriquePayer(rubriquePayer);
    }

    public RubriquePayer miseAjourEncaissement(RubriquePayer rubriquePayer,Double montantVerse){
       RubriquePayer existsRubriquePayer =  rubriquePayerService.getRubriquePayerByCode(rubriquePayer.getRbpCode()); 
       if((existsRubriquePayer.getRbpMontant() + montantVerse) >= existsRubriquePayer.getRbpPrevu())
            existsRubriquePayer.setRbpMontant(existsRubriquePayer.getRbpPrevu());
        else
            existsRubriquePayer.setRbpMontant(existsRubriquePayer.getRbpMontant() + montantVerse);
        existsRubriquePayer.setRbpMontantRestant(existsRubriquePayer.getRbpPrevu() - existsRubriquePayer.getRbpMontant());
       return rubriquePayerService.updateRubriquePayer(existsRubriquePayer.getRbpCode(), existsRubriquePayer);
    }


    

    public Double sommeMontantRubrique(List<RubriqueModel> rubriqueModels){
        return rubriqueModels.stream()
                        .mapToDouble(RubriqueModel::getRubMontant)
                        .sum();
    }


    public List<Versement> getVersementsByAnneeInscription(int currentYear){
        return versementService.getVersementsByAnneeInscription(currentYear);
    }

    public List<Versement> getVersementsByAnneeInscription(){
        return versementService.getVersementsByAnneeInscription();
    }

    /* 
     * @GaiusYan
     * Example : 
     * GET : http://localhost:8080/api/versement/between?startDate=2024-01-03&endDate=2025-01-03
     */
    public List<Versement> getVersementsByAnneeAcademiqueBetween(LocalDate startDate,LocalDate endDate){
        if(!Utils.isDateInferieur(startDate, endDate))
            throw new RuntimeException(String.format("La date %s doit venir avant la date %s",endDate,startDate));
        return versementService.getVersementsByAnneeAcademiqueBetween(startDate, endDate);
    }

    public List<Versement> getVersementByDateVersement(LocalDate localDate){
        return versementService.getVersementByDateVersement(localDate);
    }

    //public void miseAjourEtatInscription(Inscription)

   
}