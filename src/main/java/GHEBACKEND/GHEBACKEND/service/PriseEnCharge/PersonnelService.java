package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import java.lang.StackWalker.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.query.IllegalSelectQueryException;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.Exception.CustomException;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.EmploiRepo;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.ServiceRepo;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.DirectionRepository;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.PersonnelRepository;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonnelService {

    private final PersonnelRepository personnelRepository;
    /* 
    private final DirectionRepository directionRepository;
    private final ServiceRepo serviceRepository;
    private final EmploiRepo emploiRepository;
     * Ajouter une nouvelle personne
     * @GaiusYan
     */
    public PersonnelModel createPersonnel(PersonnelModel personnelModel){

        /* 
         * Vérifier si le personnel existe
         * @GaiusYan
         */
        boolean existsPersonnel = personnelRepository.existsByPerNomAndPerPrenom(personnelModel.getPerNom(), personnelModel.getPerPrenom());
        if(!existsPersonnel){
            if(checkPersonnelAge(personnelModel.getPerDateNais())){
                /* *
                 * On vérifie si son age correspond 
                 * 
                 */
                personnelModel.setPerCode(getPersonnelCode());
                personnelModel.setPerDateCreation(LocalDateTime.now());
                personnelModel.setPerVersion(String.valueOf(1));
                /* 
                 * Juste pour les test unitaires
                 * @GaiusYan
                 */
                /* personnelModel.setDirection(directionRepository.findById(1001).orElseThrow(()-> new IllegalStateException("Cette direction n'existe pas")));
                personnelModel.setService(serviceRepository.findById(1001).orElseThrow(()-> new IllegalStateException("Ce service n'existe pas")));
                personnelModel.setEmploi(emploiRepository.findById(1001).orElseThrow(()-> new IllegalStateException("Cet emploi n'existe pas"))); */
                if (Utils.getNumberYear(personnelModel.getPerDateNais(), LocalDate.now()) >= 18) {    
                    return personnelRepository.save(personnelModel);
                }else throw illegalStateException("Le personnel doit avoir au minimum 18 ans");
            }else throw illegalStateException("La date de naissance non conforme...");
        }else
            throw new IllegalStateException("Cette personnel existe déjà...");
        
    }

    public Boolean checkPersonnelAge(LocalDate localDate){
        return Utils.isDateInferieur(localDate, LocalDate.now()) ? true : false;
    }

    public IllegalStateException illegalStateException(String message){
        return new IllegalStateException(message);
    }


    /* 
     * Retourner la liste des personnel
     */
    public List<PersonnelModel> getAllPersonnel(){
        return this.personnelRepository.findAll();
    }

    /* 
    * Cette fonction permet de retourner une personne 
    * @GaiusYan
    */
    public PersonnelModel getPersonnelById(int code){
        return personnelRepository.findById(code).orElseThrow(() -> new IllegalStateException("Cette personne n'existe pas"));
    }


    /* imcrement personnel code 
     * @GaiusYan
     * Cette fonction retourne un type de code de forme 202400001
    */
    public Integer getPersonnelCode(){
        try {
            /* 
             * Cette fonction permet d'incrémenter le code du personnel 
             * Exemple : si le dernier code est 20240004 cette fonction retourne 20240005
             */
            if(Objects.equals(String.valueOf(personnelRepository.findMaxPerCode()).substring(0, 4),String.valueOf(LocalDate.now().getYear()))){
               return Integer.parseInt(String.valueOf(LocalDate.now().getYear()).concat(Utils.formatString(String.valueOf(Utils.incrementValue(String.valueOf(personnelRepository.findMaxPerCode()).substring(5, 9))))));  
           }return  Integer.parseInt(String.valueOf(LocalDate.now().getYear()).concat("00001"));
        }
        catch (Exception e) {
            System.out.println("Erreur"+ e);
            /* 
             * Dans le cas où aucune valeur n'est retrouvé dans la base de données 
             * @GaiusYan
             */
            return Integer.parseInt(String.valueOf(LocalDate.now().getYear()).concat("00001"));
        }
    }


    /* 
     * Mise à jour des informations du personnel 
     * @GaiusYan
     */
    public PersonnelModel updatePersonnel(@NonNull Integer code, PersonnelModel personnelModel){
        PersonnelModel personnelModelExist = personnelRepository.findById(code)
        .orElseThrow(() -> new IllegalStateException("Cette personnel n'existe pas..."));

        if (personnelModel.getPerNom() != null && !personnelModelExist.getPerPrenom().isEmpty() && !Objects.equals(personnelModel.getPerNom(), personnelModelExist.getPerNom())) {
            personnelModelExist.setPerNom(personnelModel.getPerNom());   
        }

        if (personnelModel.getPerPrenom() != null && !personnelModelExist.getPerPrenom().isEmpty() && !Objects.equals(personnelModel.getPerPrenom(), personnelModelExist.getPerPrenom())) {
            personnelModelExist.setPerPrenom(personnelModel.getPerPrenom());   
        }

        if (personnelModel.getPerAdresse() != null && !personnelModelExist.getPerAdresse().isEmpty() && !Objects.equals(personnelModel.getPerAdresse(), personnelModelExist.getPerAdresse())) {
            personnelModelExist.setPerAdresse(personnelModel.getPerAdresse());   
        }

        if (personnelModel.getPerEmail() != null && !personnelModelExist.getPerEmail().isEmpty() && !Objects.equals(personnelModel.getPerEmail(), personnelModelExist.getPerEmail())) {
            personnelModelExist.setPerEmail(personnelModel.getPerEmail());   
        }

        if (personnelModel.getPerSexe() != null && !personnelModelExist.getPerSexe().isEmpty() && !Objects.equals(personnelModel.getPerSexe(), personnelModelExist.getPerSexe())) {
            personnelModelExist.setPerSexe(personnelModel.getPerSexe());   
        }

        if (personnelModel.getPerDateNais() != null && !personnelModelExist.getPerDateNais().toString().isEmpty() && !Objects.equals(personnelModel.getPerDateNais(), personnelModelExist.getPerDateNais())) {
            personnelModelExist.setPerDateNais(personnelModel.getPerDateNais());   
        }
        
        if(Utils.isDateSuperieur(LocalDate.now(), personnelModel.getPerDateNais()))
            personnelModelExist.setPerDateNais(personnelModel.getPerDateNais());
        else
            throw new IllegalStateException("La date de naissance non conforme...");

        personnelModelExist.setPerVersion(Utils.incrementValue(personnelModelExist.getPerVersion()).toString());
        if (Utils.getNumberYear(personnelModel.getPerDateNais(), LocalDate.now()) >= 18) {    
            return personnelRepository.save(personnelModelExist);
        }else throw illegalStateException("Le personnel doit avoir au minimum 18 ans");
    }

    /* 
     * Suppression d'un personnel 
     * @GaiusYan
     */
    public void deletePersonnel(@NonNull Integer code){
        boolean personnelExist = personnelRepository.existsById(code);
        if (!personnelExist) {
            throw new IllegalStateException("Cette personne n'existe pas");
        }
        personnelRepository.deleteById(code);
    }
}

