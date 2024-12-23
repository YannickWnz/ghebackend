package GHEBACKEND.GHEBACKEND.service.Inscription.HistoriqueRubriqueInscription;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.HistoriqueRubriqueInscription;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.repository.Inscription.HistoriqueRubriqueInscriptionRepository;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.ClasseService;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.RubriqueService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoriqueRubriqueInscriptionService {

    private final HistoriqueRubriqueInscriptionRepository historiqueRubriqueInscriptionRepository;
    

    public HistoriqueRubriqueInscription getHistoriqueRubriqueHistoriqueById(Integer code){
        return this.historiqueRubriqueInscriptionRepository.findById(code)
            .orElseThrow(
                () -> new IllegalStateException(String.format("Cette historique n'existe pas", code ))
            );
    }


    public HistoriqueRubriqueInscription createHistoriqueRubriqueInscription(
        HistoriqueRubriqueInscription historiqueRubriqueInscription
        ){
            return historiqueRubriqueInscriptionRepository
                .save(historiqueRubriqueInscription);
    }

    public List<HistoriqueRubriqueInscription> getHistoriqueRubriqueInscriptionByInscription(
       Inscription inscription
        ){
            return historiqueRubriqueInscriptionRepository.findByInscription(inscription);
    }

    public HistoriqueRubriqueInscription updateHistoriqueRubriqueInscription(
        Integer codeHistoriqueRubriqueInscription,
        HistoriqueRubriqueInscription historiqueRubriqueInscription
        ){

            HistoriqueRubriqueInscription existsHistoriqueRubriqueInscription = 
                this.getHistoriqueRubriqueHistoriqueById(codeHistoriqueRubriqueInscription);

            if (historiqueRubriqueInscription.getHisRubLib() != null &&
                !historiqueRubriqueInscription.getHisRubLib().isEmpty() &&
                !Objects.equals(
                    historiqueRubriqueInscription.getHisMontantPrevu(),
                    existsHistoriqueRubriqueInscription.getHisMontantPrevu()) 

                    ) {
                existsHistoriqueRubriqueInscription.setHisRubLib(
                    historiqueRubriqueInscription.getHisRubLib());
            }

            if (historiqueRubriqueInscription.getHisRubLib() != null &&
                !historiqueRubriqueInscription.getHisRubLib().isEmpty() &&
                !Objects.equals(
                    historiqueRubriqueInscription.getHisMontantPrevu(),
                    existsHistoriqueRubriqueInscription.getHisMontantPrevu()) 

                    ) {
                existsHistoriqueRubriqueInscription.setHisRubLib(
                    historiqueRubriqueInscription.getHisRubLib());
            }

            return historiqueRubriqueInscriptionRepository
                .save(existsHistoriqueRubriqueInscription);
    } 

    

}
