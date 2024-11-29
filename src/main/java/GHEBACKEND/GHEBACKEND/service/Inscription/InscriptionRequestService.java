package GHEBACKEND.GHEBACKEND.service.Inscription;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscriptionRequestService {
    private final InscriptionService service;

    public InscriptionRequest inscrire(InscriptionRequest request){
        Inscription inscription = new Inscription();
        inscription.setAnneeAcademique(request.getAnnee());
        inscription.setClasse(request.getClasse());
        inscription.setEtudiant(request.getEtudiant());
        inscription.setInsCode(service.generateInscriptionCode());
        inscription.setInsDate(LocalDate.now());
        inscription.setInsCreerPar(request.getInsCreerPar());
        inscription.setInsModifierPar(request.getInsModifierPar());
        inscription.setInsVersion(1);
        service.createInscription(inscription);
        return request;
    }
}
