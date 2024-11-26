package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.PersonnelRepository;
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
        /* personnelModel.getPerCode(personnelRepository.findMaxPerCode().orElse(String.valueOf(LocalDate.now().getYear())); */
        Integer perCode = 
        return personnelModel;
    }
}
