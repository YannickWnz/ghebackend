package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.LienModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.LienRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class LienService {

    @Autowired
    private LienRepo lienRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<LienModel> getAllLienData() {

        List<LienModel> lienData = lienRepo.findAll();

        return lienData;
    
    }

    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }

    public void addNewLien(LienModel lienModel) {

        // recuperation du code apres generation par la methode utilitaire
        Integer code = utilityMethods.codeGenerator("LIE_CODE", "T_LIEN");
                
        // version par default a la creation
        Integer defaultVersion = 1;

        // getting datetime from utilityMethod class
        String currentDateTime = utilityMethods.getCurrentDateTime();

        lienModel.setLieCode(code);
        lienModel.setLieVersion(defaultVersion);
        lienModel.setLieDateCreation(currentDateTime);

        lienRepo.save(lienModel);


    }

    public void updateLienData(Integer code, LienModel lienModel) {

        // get current promotion version from repo function
        Integer currentVersion = utilityMethods.getCurrentVersion(code, "LIE_CODE", "T_LIEN", "LIE_VERSION");

        // get a new version by increment current by 1
        Integer newVersion = currentVersion + 1;
        
        LienModel existingLienData = lienRepo.findById(code)
            .orElseThrow(() -> new IllegalArgumentException("Could not find data ..."));

        existingLienData.setLieLib(lienModel.getLieLib());
        existingLienData.setLieModifierPar(lienModel.getLieModifierPar());
        existingLienData.setLieVersion(newVersion);

        lienRepo.save(existingLienData);

    }

    public void deleteLienData(Integer code) {

        if(lienRepo.existsById(code)) {
            lienRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Cound not find data with provided code ...");
        }

    }

    @SuppressWarnings("deprecation")
    public String fetchLienLibUsingLienCode(Integer code) {
        
        String getLibUsingCodeQuery = "SELECT LIE_LIB FROM T_LIEN WHERE LIE_CODE = ?";
        
        try {
            return jdbcTemplate.queryForObject(
                getLibUsingCodeQuery,
                new Object[]{code},
                String.class               
            );
        } catch (Exception e) {
            // Handle any exceptions (e.g., if no record found)
            System.err.println("Error fetching LIE_LIB for LIE_CODE " + code + ": " + e.getMessage());
            return null; // Or handle with custom error message
        }

    }

}
