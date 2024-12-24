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
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.EtudiantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RubriquePayerRequestService {

    private final RubriquePayerService rubriquePayerService;
    private final InscriptionService inscriptionService;
    private final EtudiantService etudiantService;

    public List<RubriquePayerResponse> getRubriquesByInscription(Integer inscriptionCode){

        if (Objects.equals(inscriptionCode, null)) throw new EntityNotFoundException("Aucun numero d'inscription...");

        Inscription inscription = inscriptionService.getInscriptionById(inscriptionCode);
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


    public List<RestePayerResponse> afficherRubriqueNonSolde(Integer etudiantCode){

        EtudiantModel etudiant = etudiantService.getEtudiantByEtuCode(etudiantCode);
        List<Integer> inscriptionCodes = new ArrayList<>();
        Integer inscriptionCode = inscriptionService.getMaximumInscriptionByEtudiant(etudiant);

        if (Objects.equals(inscriptionCode, null)) return new ArrayList<>();
        
        inscriptionCodes.add(
            inscriptionService
                .getInscriptionById(inscriptionCode).getInsCode()
        );


        List<Inscription> inscriptions = inscriptionService
            .getInscriptionByEtudiantAndInsCodeNotIn(etudiant, inscriptionCodes);

        List<RestePayerResponse> responses = new ArrayList<>();

        for (Inscription inscription : inscriptions) {
            
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
        return responses;
    }


}
