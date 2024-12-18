package GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RubriquePayerRequestService {

    private final RubriquePayerService rubriquePayerService;
    private final InscriptionService inscriptionService;

    public List<RubriquePayerResponse> getRubriquesByInscription(Integer code){

        if (Objects.equals(code, null)) throw new EntityNotFoundException("Aucun numero d'inscription...");

        Inscription inscription = inscriptionService.getInscriptionById(code);
        List<RubriqueModel> rubriques = inscription.getClasse().getRubrique();
        List<RubriquePayerResponse> rubriquePayerResponses = new ArrayList<>();

        for (RubriqueModel rubrique : rubriques) {
            
            RubriquePayer rubriquePayer = rubriquePayerService
                .getRubriquePayerByRubriqueAndInscription(rubrique, inscription);

            if (!Objects.equals(rubriquePayer, null)) {
                rubriquePayerResponses.add(
                    RubriquePayerResponse
                        .builder()
                        .code(rubrique.getRubCode())
                        .designation(rubrique.getRubLib())
                        .datePaiement(rubriquePayer.getRbpDate())
                        .montantPrevu(rubriquePayer.getRbpPrevu())
                        .montantPayer(rubriquePayer.getRbpMontant())
                        .montantRestant(rubriquePayer.getRbpMontantRestant())
                        .isSold(rubriquePayer.isSold())
                        .build()
                );
            }else{
                rubriquePayerResponses.add(
                    RubriquePayerResponse
                        .builder()
                        .code(rubrique.getRubCode())
                        .designation(rubrique.getRubLib())
                        .datePaiement(null)
                        .montantPrevu(rubrique.getRubMontant())
                        .montantPayer(null)
                        .montantRestant(null)
                        .isSold(false)
                        .build()
                );
            }
        }

        return rubriquePayerResponses;
    }


    public List<RestePayerResponse> getRubriqueisSoldFalseByEtudiant(EtudiantModel etudiant){

        List<Inscription> inscriptions = inscriptionService.getInscriptionByEtudiant(etudiant);
        List<RestePayerResponse> responses = new ArrayList<>();

        for (Inscription inscription : inscriptions) {
            //TO DO: Retourner la liste des rubriques n'ayant qui ne sont pas encore soldé
            List<RubriquePayerResponse> rubriquePayerResponses = getRubriquesByInscription(inscription.getInsCode());
            for (RubriquePayerResponse rubriquePayerResponse : rubriquePayerResponses) {
                if (!rubriquePayerResponse.isSold()) {

                    responses.add(
                        RestePayerResponse.builder()
                            .rubriquePayerResponse(rubriquePayerResponse)
                            .inscription(inscription)
                            .build()
                    );
                }
            }
        }
        return null;
    }


}
