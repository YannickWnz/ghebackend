package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.DirectionModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.DirectionRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class DirectionService {

    @Autowired
    private DirectionRepo directionRepo;

    
    @Autowired
    private UtilityMethods utilityMethods;

    
    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }

    public List<DirectionModel> getAllDirectionData() {

        List<DirectionModel> directionData = directionRepo.findAll();

        return directionData;
    }

    public void addNewDirectionData(DirectionModel directionModel) {

        
        // recuperation du code apres generation par la methode utilitaire
        Integer code = utilityMethods.codeGenerator("DIR_CODE", "T_DIRECTION");
        
        // version par default a la creation
        Integer defaultVersion = 1;

        // getting datetime from utilityMethod class
        String currentDateTime = utilityMethods.getCurrentDateTime();

        directionModel.setDirCode(code);
        directionModel.setDirVersion(defaultVersion);
        directionModel.setDirDateCreation(currentDateTime);

        directionRepo.save(directionModel);

    }

    public void updateDirectionData(Integer code, DirectionModel directionModel) {
        // get current promotion version from repo function
        Integer currentVersion = utilityMethods.getCurrentVersion(code, "DIR_CODE", "T_DIRECTION", "DIR_VERSION");
        
        // get a new version by increment current by 1
        Integer newVersion = currentVersion + 1;

        DirectionModel existingDirectionData = directionRepo.findById(code).orElseThrow(() -> new IllegalArgumentException("Could not find data..."));

        existingDirectionData.setDirModifierPar(directionModel.getDirModifierPar());
        existingDirectionData.setDirVersion(newVersion);
        existingDirectionData.setDirLib(directionModel.getDirLib());

        directionRepo.save(existingDirectionData);

    }

    public void deleteDirectionData(Integer code) {

        if(directionRepo.existsById(code)) {
            directionRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Coulndt find data ...");
        }

    }

}
