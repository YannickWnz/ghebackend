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
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.RubriqueRepo;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VersementRequestService  {

    private final VersementService versementService;
    private final RubriquePayerService rubriquePayerService;
    private final InscriptionService inscriptionService;
    private final RubriqueRepo rubriqueRepository;

    public VersementResponse versement(VersementRequest request){
       Inscription inscription =  inscriptionService.getInscriptionById(request.getInscription().getInsCode());
       //Recherche des rubriques pour la classe à laquelle l'Etudiant s'est inscrit

       //Recupération des rubriques de la classe liée à l'inscription
        List<RubriqueModel> rubriques = Optional
            .ofNullable(inscription.getClasse().getRubrique())
            .orElseThrow(() -> new IllegalStateException("Aucune rubrique n'est liée à cette classe"));

        Double montant = request.getMontantVerse();
        VersementResponse response = new VersementResponse();

        if(montant == null){
            throw new IllegalStateException("Aucun montant renseigné");
        }
        if(!Objects.equals(rubriques, null)){           
             
                List<RubriqueModel> rubriqueModels = rubriqueRepository.findByClasseOrderByRubFraisUniqueDescRubOrdreAsc(
                    inscription.getClasse());
                List<RubriquePayer> rubriquePayers = inscription.getRubliquesPayes();
                //Récuperations de toutes les rubriques obligatoires
                List<RubriqueModel> rubriqueObligatoire = rubriqueRepository
                .findByClasseAndRubFraisUniqueTrueOrderByRubFraisUniqueAscRubOrdreAsc(inscription.getClasse());
                //On fait la somme des montants obligatoires
                 Double montantAverserObligatoire = sommeMontantRubrique(rubriqueObligatoire); 
                //Montant à verser
                Double montantRestant = request.getMontantVerse();
                boolean flag = false;
                //Déjà effectué un paiement
                if (rubriquePayers.size() > 0) {

                        for (RubriqueModel rubriqueModel : rubriqueModels) {
                                RubriquePayer rubriquePayer = rubriquePayerService.getRubriquePayer(inscription, rubriqueModel);
                                if(!Objects.equals(rubriquePayer, null)){
                                    if(rubriquePayer.getRbpMontantRestant() > 0
                                    ){
                                        miseAjourEncaissement(rubriquePayer, montantRestant);
                                        flag = true;
                                        montantRestant -= rubriqueModel.getRubMontant();
                                    }else {
                                        response.setDescription(String.format("La rubrique %s est déjà soldée",rubriqueModel.getRubLib()));
                                        response.setMessage("Attention");
                                    }
                                }else{
                                    nouvelEncaissement(inscription,rubriqueModel,montantRestant);
                                    flag = true;
                                    montantRestant -= rubriqueModel.getRubMontant();
                                }  
                        }
                        //Effectuer un versement
                        if(flag){
                            verser(inscription, request.getMontantVerse());
                            response.setDescription("Versement effectué avec succès");
                            response.setMessage("Succès");
                            return response;
                        }else throw new IllegalStateException("Toutes les rubriques sont soldées");
                        
                }else{
                    //Première inscription

                    if(request.getMontantVerse() >= montantAverserObligatoire){

                        for (RubriqueModel rubriqueModel : rubriqueModels) {
                            if(montantRestant >= rubriqueModel.getRubMontant() && rubriqueModel.getRubFraisUnique()){
                                nouvelEncaissement(inscription, rubriqueModel,rubriqueModel.getRubMontant());
                            }else
                                nouvelEncaissement(inscription, rubriqueModel, montantRestant);
                            montantRestant -= rubriqueModel.getRubMontant();
                        }
                        //Effectuer un versement
                        verser(inscription, request.getMontantVerse());
                        response.setDescription("Versement effectué avec succès");
                        response.setMessage("Succès");
                    }else 
                        throw new IllegalStateException(
                            String.format(
                                "Ce montant ne peut pas couvrir toutes les rubriques obligatoires pour cette inscription, il faut atteindre %s FCFA", 
                                montantAverserObligatoire)
                            );
                }
        }else{
            throw new 
            IllegalStateException(
                String.format(
                    "Aucune rubrique n'est disponible pour la salle %s",
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

    public RubriquePayer 
    nouvelEncaissement(Inscription inscription,
                        RubriqueModel rubriqueModel,
                        Double montantVerse){
        RubriquePayer rubriquePayer = new RubriquePayer();
        rubriquePayer.setInscription(inscription);
        rubriquePayer.setRubrique(rubriqueModel);
        rubriquePayer.setRbpMontant(montantVerse);
        rubriquePayer.setRbpPrevu(rubriqueModel.getRubMontant());
        rubriquePayer.setRbpMontantRestant(rubriqueModel.getRubMontant() - montantVerse);
        rubriquePayer.setRbpDate(LocalDate.now());
        return rubriquePayerService.createRubriquePayer(rubriquePayer);
    }

    public RubriquePayer miseAjourEncaissement(RubriquePayer rubriquePayer,Double montantVerse){
       RubriquePayer existsRubriquePayer =  rubriquePayerService.getRubriquePayerByCode(rubriquePayer.getRbpCode()); 
       if((existsRubriquePayer.getRbpMontant() + montantVerse) >= existsRubriquePayer.getRbpPrevu()){
            existsRubriquePayer.setRbpMontant(existsRubriquePayer.getRbpPrevu());
        }else{
            existsRubriquePayer.setRbpMontant(existsRubriquePayer.getRbpMontant() + montantVerse);
        }
        existsRubriquePayer.setRbpMontantRestant(existsRubriquePayer.getRbpPrevu() - existsRubriquePayer.getRbpMontant());
       return rubriquePayerService.updateRubriquePayer(existsRubriquePayer.getRbpCode(), existsRubriquePayer);
    }

    /* 
     * Cette fonction permet de récuperer la liste des rubrique et de faire la somme
     * @GaiusYan
     */
    public Double sommeMontantRubrique(List<RubriqueModel> rubriqueModels){
        return rubriqueModels.stream()
                        .mapToDouble(RubriqueModel::getRubMontant)
                        .sum();
    }
}