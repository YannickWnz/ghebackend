package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Promotion;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.PromotionRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class PromotionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PromotionRepo promotionRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    // function fetching all promo ref data
    public List<Promotion> getAllPromoRefData() {

        List<Promotion> promoRefData = promotionRepo.findAll();
        
        return promotionRepo.findAll();
    }

    // service function inserting promotion donnees ref to db
    // @SuppressWarnings("deprecation")
    public void addPromotion(Promotion promotion) {

        // recuperation du code apres generation par la methode utilitaire
        Integer code = utilityMethods.codeGenerator("PRO_CODE", "T_PROMOTIONS");
        
        // version par default a la creation
        Integer defaultVersion = 1;

        // getting datetime from utilityMethod class
        String currentDateTime = utilityMethods.getCurrentDateTime();

        promotion.setProCode(code);
        promotion.setProVersion(defaultVersion);
        promotion.setProDateCreation(currentDateTime);

        promotionRepo.save(promotion);
    }

    // function handling data update
    public Promotion updatePromoData(Integer proCode, String proLib, String proModifierPar) {

        // get current promotion version from repo function
        // recuperation de la version courante en utilisant la method definit dans l'interface promotionRepo
        int currentVersion = promotionRepo.findProVersion(proCode);
        
        // get a new version by increment current by 1
        int newVersion = currentVersion + 1;
        
        
        // find data before update else throw error
        // recherche de la donnee ... throw err si non trouvee 
        Promotion existingPromoData = promotionRepo.findById(proCode)
        .orElseThrow(() -> new IllegalArgumentException("Could not find data with given code"));
        // set new data then save ...
        existingPromoData.setProLib(proLib);
        existingPromoData.setProModifierPar(proModifierPar);
        existingPromoData.setProVersion(newVersion);

        return promotionRepo.save(existingPromoData);


    }

    // function handling data deletion 
    public void deletePromoData(Integer proCode) {

        // check if data exists before delete
        if(promotionRepo.existsById(proCode)) {
            promotionRepo.deleteById(proCode);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }

    // function handling the fetch of promotion data in an Annee Ac 
    // function qui se charge de la recuperation des promotion dans une annee academique 
    
    // @SuppressWarnings("deprecation")
    // public List<Promotion> getPromotionDataInAac(Integer aacCode) {

    //     String query = "SELECT t.PRO_LIB, t.PRO_CODE FROM T_PROMOTION t WHERE t.AAC_CODE = ?";

    //     // return jdbcTemplate.queryForObject(query, new Object[]{aacCode}, Integer.class);

    //     return promotionRepo.getPromotionDataInAnneeAcademique(aacCode);

    // }
    
    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }



}
