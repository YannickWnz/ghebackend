package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        personnelModel.setPerCode(getPersonnelCode());
        personnelModel.setPerDateCreation(LocalDateTime.now());
        return personnelModel;
    }


    /* imcrement personnel code 
     * @GaiusYan
     * 
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
