package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.RubriqueRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class RubriqueService {

    @Autowired
    private RubriqueRepo rubriqueRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    public List<RubriqueModel> getAllRubrique() {
        return rubriqueRepo.findAll();
    }

    public void addNewRubrique(RubriqueModel rubriqueModel) {

        Integer code = utilityMethods.codeGenerator("RUB_CODE", "T_RUBRIQUE");

         // version definie sur 1 par default a la creation
         Integer version = 1;
        
         // getting datetime from utilityMethod class
         String currentDateTime = utilityMethods.getCurrentDateTime();
 
 
         rubriqueModel.setRubCode(code);
         rubriqueModel.setRubVersion(version);
         rubriqueModel.setRubDateCreation(currentDateTime);

         rubriqueRepo.save(rubriqueModel);

    }

    public void updateRubriqueData(Integer code, RubriqueModel rubriqueModel) {

        Integer newVersion = utilityMethods.getCurrentVersion(code, "RUB_CODE", "T_RUBRIQUE", "RUB_VERSION") + 1;

        // find data before update else throw error
        RubriqueModel existingRubriqueData = rubriqueRepo.findById(code)
        .orElseThrow(() -> new IllegalArgumentException("Could not find data with given code"));
        
        // set new data then save ...
            existingRubriqueData.setRubLib(rubriqueModel.getRubLib());
            existingRubriqueData.setRubMontant(rubriqueModel.getRubMontant());
            existingRubriqueData.setRubFraisUnique(rubriqueModel.getRubFraisUnique());
            existingRubriqueData.setRubModifierPar(rubriqueModel.getRubModifierPar());
            existingRubriqueData.setRubVersion(newVersion);

            rubriqueRepo.save(existingRubriqueData);

    }

    public void deleteRubriqueData(int code) {

        if(rubriqueRepo.existsById(code)) {
            rubriqueRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }
    
    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }

}
