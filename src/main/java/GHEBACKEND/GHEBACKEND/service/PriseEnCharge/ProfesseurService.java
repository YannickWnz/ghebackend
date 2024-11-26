package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;
import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.ProfesseurModel;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.ProfesseurRepository;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;


/* 
 * Documentation de code par 
 * @GaiusYan
 */
@Service
@RequiredArgsConstructor
public class ProfesseurService {
    private final ProfesseurRepository professeurRepository; 

    /* Ce service permet de créer un professeur
     * @GaiusYan
     */
    public ProfesseurModel createProfesseur(ProfesseurModel professeurModel){
        /* Cette fonction si dessous permet de recherche un professeur avec son nom et prénom
         * et retourner une boolean si et trouvé ou nom
         * @GaiusYan
         */
        boolean existsProfesseur = professeurRepository.existsByProNomAndProPrenom(professeurModel.getProNom(),professeurModel.getProPrenom());
        if (!existsProfesseur) {
            /* 
             * Si ce professeur n'existe pas alors on l'enregistre
             * @GaiusYan
             */
           return professeurRepository.save(professeurModel);
        }
        /* Si le professeur est retrouvé un retourne une exception, avec le message ci-dessous */
        throw new IllegalStateException("Le professeur existe déjà");
    }


    /* Retourner la liste des professeurs
     * @GaiusYan
     */
    public List<ProfesseurModel> getAllProfesseur(){
        return professeurRepository.findAll();
    }

    /* 
     * Cette fonction permet de retourner un professeur 
     * @GaiusYan
     * Si le professeur n'existe pas il retourne une exception
     * @GaiusYan
     *      */
    public Optional<ProfesseurModel> getProfesseurByCode(@NonNull String code){
        return Optional.ofNullable(professeurRepository.findById(code))
        .orElseThrow(() -> new IllegalStateException("Le professeur n'existe pas..."));
    }


    /* Cette fonction permet de faire la mise à jour d'un professeur
     * 
     * @GaiusYan
     */
    public ProfesseurModel updateProfesseur(@NonNull String code, ProfesseurModel professeurModel){

        /* 
         * 
         * On vérifie ce code existe dans la base de données
         * Dans le cas écheant on retourne un message d'erreur
         * @GaiusYan
         */
        ProfesseurModel professeurModelExists = professeurRepository.findById(code)
            .orElseThrow(() -> new IllegalStateException("Ce professeur n'existe pas"));
        /* 
         * Les controles effectués ci-dessous permet de :
         *  - Controler si une propriété n'est pas null;
         *  - Qu'elle est différente de vide exemple: propriété != ""
         *  - Verifier si le nom dans la base de données et celui insérer ne sont pas identiques
         * @GaiusYan
         */
        if(professeurModel.getProNom() != null && !professeurModel.getProNom().isEmpty() && !Objects.equals(professeurModel.getProNom(), professeurModelExists.getProNom())){
            professeurModelExists.setProNom(professeurModel.getProNom());
        }
        if(professeurModel.getProPrenom() != null && !professeurModel.getProPrenom().isEmpty() && !Objects.equals(professeurModel.getProPrenom(), professeurModelExists.getProPrenom())){
            professeurModelExists.setProPrenom(professeurModel.getProPrenom());
        }
        if(professeurModel.getProAdresse() != null && !professeurModel.getProAdresse().isEmpty() && !Objects.equals(professeurModel.getProAdresse(), professeurModelExists.getProAdresse())){
            professeurModelExists.setProAdresse(professeurModel.getProAdresse());
        }
        if(professeurModel.getProTelephone() != null && !professeurModel.getProTelephone().isEmpty() && !Objects.equals(professeurModel.getProTelephone(), professeurModelExists.getProTelephone())){
            professeurModelExists.setProTelephone(professeurModel.getProTelephone());
        }
        return professeurRepository.save(professeurModelExists);
    }

    /* 
     * cette procédure permet de supprimer un professeur dans la base de données
     * @GaiusYan
     */
    public void deleteProfesseur(@NonNull String code){
        boolean professeurExists = professeurRepository.existsById(code);
        if(professeurExists){
            professeurRepository.deleteById(code);
        }
        throw new IllegalStateException("Ce professeur n'existe pas...");
    }
}
