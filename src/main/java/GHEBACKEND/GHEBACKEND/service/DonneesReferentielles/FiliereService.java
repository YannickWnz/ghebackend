package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Filiere;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.FiliereRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepo filiereRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UtilityMethods utilityMethods;

    // method qui se charge de la recuperation de toutes les filieres
    public List<Filiere> getAlFiliere() {
        return filiereRepo.findAll();
    }

    // update method
    public void updatePromoData(Integer filCode, String filLib, String filModifierPar) {

        // get current promotion version from repo function
        int currentVersion = utilityMethods.getCurrentVersion(filCode, "FIL_CODE", "T_FILIERE", "FIL_VERSION");
        
        // get a new version by increment current by 1
        int newVersion = currentVersion + 1;
        
        
        // find data before update else throw error
        Filiere existingFiliereData = filiereRepo.findById(filCode)
        .orElseThrow(() -> new IllegalArgumentException("Could not find data with given code"));
        
        // set new data then save ...
        existingFiliereData.setFilLib(filLib);
        existingFiliereData.setFilModifierPar(filModifierPar);
        existingFiliereData.setFilVersion(newVersion);

        filiereRepo.save(existingFiliereData);

    }

    public void addNewFiliereData(Filiere filiere) {

        Integer code = utilityMethods.codeGenerator("FIL_CODE", "T_FILIERE");

         // version definie sur 1 par default a la creation
         Integer version = 1;
        
         // getting datetime from utilityMethod class
         String currentDateTime = utilityMethods.getCurrentDateTime();
 
 
         filiere.setFilCode(code);
         filiere.setFilVersion(version);

         filiereRepo.save(filiere);

    }

    
    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }


}
