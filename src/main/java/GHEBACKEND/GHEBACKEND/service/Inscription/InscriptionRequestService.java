package GHEBACKEND.GHEBACKEND.service.Inscription;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.EtudiantRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscriptionRequestService {
    private final InscriptionService service;
    private final EtudiantRepo repository;

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
        inscription.setEtudiant(request.getEtudiant());
        inscription.setAnneeAcademique(request.getAnnee());
        inscription.setClasse(request.getClasse());
        inscription.setPromotion(request.getPromotion());
        /* 
         * Test
         */
       /*  inscription.setEtudiant(repository.findById(1).get()); */
        service.createInscription(inscription);
        return new InscriptionResponse("Inscription effectuée avec succès","Succès");
    }
}
