package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.VolumeHoraire;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.VolumeHoraireRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class VolumeHoraireService {

    @Autowired
    private VolumeHoraireRepo volumeHoraireRepo;

    @Autowired
    private UtilityMethods utilityMethods;


    public List<VolumeHoraire> getAllVolumeHoraires() {
        return volumeHoraireRepo.findAll();
    }
    
    public void addNewVolumeHoraire(VolumeHoraire volumeHoraire) {

        Integer code = utilityMethods.codeGenerator("CLM_CODE", "T_CLASSE_MATIERE");

         // version definie sur 1 par default a la creation
         Integer version = 1;
        
         // getting datetime from utilityMethod class
         String currentDateTime = utilityMethods.getCurrentDateTime();
 
 
         volumeHoraire.setClmCode(code);
         volumeHoraire.setClmVersion(version);
         volumeHoraire.setClmDateCreation(currentDateTime);

         volumeHoraireRepo.save(volumeHoraire);

    }

    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }

    public void updateVolumeHoraireData(Integer code, VolumeHoraire volumeHoraire) {

        Integer newVersion = utilityMethods.getCurrentVersion(code, "CLM_CODE", "T_CLASSE_MATIERE", "CLM_VERSION") + 1;

        // find data before update else throw error
        VolumeHoraire existingVolumeHoraireData = volumeHoraireRepo.findById(code)
        .orElseThrow(() -> new IllegalArgumentException("Could not find data with given code"));
        
        // set new data then save ...
            existingVolumeHoraireData.setClmVolumeHoraire(volumeHoraire.getClmVolumeHoraire());
            existingVolumeHoraireData.setClmModifierPar(volumeHoraire.getClmModifierPar());
            existingVolumeHoraireData.setClmVersion(newVersion);

            volumeHoraireRepo.save(existingVolumeHoraireData);

    }

    public List<VolumeHoraire> getAssignedVolumeHoraire(Integer claCode, Integer matCode) {

        return volumeHoraireRepo.getAssignedVolumeHoraire(claCode, matCode);

    }

    public void deleteVolumeHoraireData(Integer code) {

        if(volumeHoraireRepo.existsById(code)) {
            volumeHoraireRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }

}
