package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import java.lang.StackWalker.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.PersonnelRepository;
import GHEBACKEND.GHEBACKEND.utils.Utils;
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
}
