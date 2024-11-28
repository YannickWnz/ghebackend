package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import java.lang.StackWalker.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.query.IllegalSelectQueryException;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.PersonnelRepository;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonnelService {

    private final PersonnelRepository personnelRepository;
    
    /* 
     * Ajouter une nouvelle personne
     * @GaiusYan
     */
    public PersonnelModel createPersonnel(PersonnelModel personnelModel){
        boolean existsPersonnel = personnelRepository.existsByPerNomAndPerPrenom(personnelModel.getPerNom(), personnelModel.getPerPrenom());
        if(!existsPersonnel){
            personnelModel.setPerCode(getPersonnelCode());
            personnelModel.setPerDateCreation(LocalDateTime.now());
            return personnelModel;
        }else{
            throw new IllegalStateException("Cette personnel existe déjà...");
        }
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
    public Optional<PersonnelModel> getPersonnelById(int code){
        return Optional.ofNullable(personnelRepository.findById(code)).orElseThrow(() -> new IllegalStateException("Cette personne n'existe pas"));
    }


    /* imcrement personnel code 
     * @GaiusYan
     * Cette fonction retourne un type de code de forme 202400001
    */
    public Integer getPersonnelCode(){
        try {
           return Integer.parseInt(String.valueOf(LocalDate.now().getYear()).concat(String.valueOf(Utils.incrementValue(String.valueOf(personnelRepository.findMaxPerCode()).substring(5, 9)))));  
        } 
        catch (Exception e) {
            System.out.println("Erreur"+ e);
            return Integer.parseInt( String.valueOf(LocalDate.now().getYear()).concat("00001"));
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
        return personnelRepository.save(personnelModelExist);
    }
}
