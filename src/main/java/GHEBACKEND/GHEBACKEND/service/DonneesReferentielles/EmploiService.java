package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.EmploiModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.EmploiRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class EmploiService {

    @Autowired
    private EmploiRepo emploiRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    // Function fetching emploi data
    public List<EmploiModel> getAllEmploiData() {

        List<EmploiModel> fetchedEmploiData = emploiRepo.findAll();

        return fetchedEmploiData;

    }

    // Function creating new emploi data
    public void addNewEmploi(EmploiModel emploiModel) {

        // recuperation du code apres generation par la methode utilitaire
        Integer code = utilityMethods.codeGenerator("EMP_CODE", "T_EMPLOI");
        
        // version par default a la creation
        Integer defaultVersion = 1;

        // getting datetime from utilityMethod class
        String currentDateTime = utilityMethods.getCurrentDateTime();


        emploiModel.setEmpCode(code);
        emploiModel.setEmpVersion(defaultVersion);
        emploiModel.setEmpDateCreation(currentDateTime);

        emploiRepo.save(emploiModel);

    }

    // Function editing existing emploi data
    public void editEmploiData(Integer code, EmploiModel emploiModel) {

        // get current promotion version from repo function
        Integer currentVersion = utilityMethods.getCurrentVersion(code, "EMP_CODE", "T_EMPLOI", "EMP_VERSION");
        
        // get a new version by increment current by 1
        Integer newVersion = currentVersion + 1;

        EmploiModel existingEmploiData = emploiRepo.findById(code)
            .orElseThrow(() -> new IllegalArgumentException("Could not find data using code provided"));
        
        existingEmploiData.setEmpLib(emploiModel.getEmpLib());
        existingEmploiData.setEmpModifierPar(emploiModel.getEmpModifierPar());
        existingEmploiData.setEmpVersion(newVersion);

        emploiRepo.save(existingEmploiData);

    }

    public void deleteEmploiData(Integer code) {

        if(emploiRepo.existsById(code)) {
            emploiRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Could not find data using code provided");
        }

    }

    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }



}
