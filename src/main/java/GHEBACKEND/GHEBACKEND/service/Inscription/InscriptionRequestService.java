package GHEBACKEND.GHEBACKEND.service.Inscription;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.AnneeAcademique;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.EtudiantRepo;
import GHEBACKEND.GHEBACKEND.security.Utilisateur.Utilisateur;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.AnneeAcademiqueService;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.ClasseService;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.PromotionService;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.EtudiantService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscriptionRequestService {
    private final InscriptionService service;
    private final EtudiantService etudiantService;
    private final PromotionService promotionService;
    private final ClasseService classeService;
    private final AnneeAcademiqueService anneeAcademiqueService;

    public InscriptionResponse inscrire(InscriptionRequest request){
        Inscription inscription = new Inscription();
        inscription.setAnneeAcademique(request.getAnnee());
        inscription.setInsNiveauValidation(0);
        inscription.setClasse(request.getClasse());
        inscription.setEtudiant(request.getEtudiant());
        inscription.setInsCode(service.generateInscriptionCode());
        inscription.setInsDate(LocalDate.now());
        inscription.setInsCreerPar(request.getInsCreerPar());
        inscription.setInsModifierPar(request.getInsModifierPar());
        inscription.setInsVersion(1);
        //Controle d'existence de ces objets dans la base des données
        inscription.setEtudiant(etudiantService.getEtudiantByEtuCode(request.getEtudiant().getEtdCode()));
        inscription.setClasse(classeService.getClasseModelByClaCode(request.getClasse().getCla_code()));
        inscription.setPromotion(promotionService.getPromotionByProCode(request.getPromotion().getProCode()));
        inscription.setAnneeAcademique(anneeAcademiqueService.getAnneeAcademiqueByAaCode(request.getAnnee().getAacCode()));
        
        service.createInscription(inscription);
        return new InscriptionResponse(
            "Inscription effectuée avec succès",
            "Succès");
    }

    public InscriptionResponse modifierInscription(Integer code,InscriptionRequest request){
        Inscription inscription = new Inscription();
        inscription.setInsCode(code);
        inscription.setAnneeAcademique(request.getAnnee());
        inscription.setInsNiveauValidation(0);
        inscription.setClasse(request.getClasse());
        inscription.setEtudiant(request.getEtudiant());
        /* inscription.setInsDate(LocalDate.now()); */
        inscription.setInsCreerPar(request.getInsCreerPar());
        inscription.setInsModifierPar(request.getInsModifierPar());
        inscription.setInsVersion(1);
        inscription.setEtudiant(request.getEtudiant());
        inscription.setAnneeAcademique(request.getAnnee());
        inscription.setClasse(request.getClasse());
        inscription.setPromotion(request.getPromotion());

       
            service.updateInscription(inscription.getInsCode(),inscription);
            return new InscriptionResponse(
                "Modification effectuée avec succès",
                "Succès");
    }


    public InscriptionResponse supprimerInscription(Integer code){
            service.deleteInscriptionById(code);
            return new InscriptionResponse(
                "Suppression effectuée avec succès",
                "Succès"
            );
    }

    public List<Inscription> getInscriptionByNiveauValidation(Integer niveauValidation){
        Utilisateur utilisateur =(Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(
            utilisateur != null &&
            utilisateur.getRole().getRolNiveauValidation().equals(niveauValidation)
        ){
            return service.getInscriptionByNiveauValidation(niveauValidation);
        }else 
            throw new IllegalStateException(
                "Vous disposez pas de l'accès"
            );
    }

    // public InscriptionResponse validerInscription(Integer code){
    //     Utilisateur utilisateur = (Utilisateur) SecurityContextHolder
    //                             .getContext().getAuthentication().getPrincipal();
    //     Inscription inscription = service.getInscriptionById(code);
    //     if(
    //         utilisateur.getRole().getRolNiveauValidation().equals(inscription.getInsNiveauValidation())
    //     ){
    //         service.validateInscription(
    //             code,
    //             utilisateur.getRole().getRolNiveauValidation()
    //         );
    //     }else 
    //     throw new RuntimeException(
    //         "Vous ne disposer pas de droit nécéssaire pour effectuer la validation de cette inscription"
    //         );
    //     return InscriptionResponse.builder()
    //             .message("Succès")
    //             .description("Validation efffectuée avec succès")
    //             .build();
    // }

    // public InscriptionResponse rejeterInscription(Integer code){
    //     Utilisateur utilisateur = (Utilisateur) SecurityContextHolder
    //                             .getContext().getAuthentication().getPrincipal();
    //     Inscription inscription = service.getInscriptionById(code);
    //     if(
    //         utilisateur.getRole().getRolNiveauValidation().equals(inscription.getInsNiveauValidation())
    //     ){
    //         service.rejeterInscription(
    //             code,
    //             utilisateur.getRole().getRolNiveauValidation()
    //         );
    //     }else 
    //     throw new RuntimeException(
    //         "Vous ne disposer pas de droit nécéssaire pour effectuer la validation de cette inscription"
    //         );
    //     return InscriptionResponse.builder()
    //             .message("Succès")
    //             .description("Validation efffectuée avec succès")
    //             .build();
    // }
}
