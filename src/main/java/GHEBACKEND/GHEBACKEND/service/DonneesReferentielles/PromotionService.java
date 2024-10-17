package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Promotion;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.PromotionRepo;

import java.util.*;

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

        Integer code = utilityMethods.codeGenerator("PRO_CODE", "T_PROMOTION");

        Integer defaultVersion = 1;

        // getting datetime from utilityMethod class
        String currentDateTime = utilityMethods.getCurrentDateTime();

        promotion.setProCode(code);
        promotion.setProVersion(defaultVersion);
        promotion.setProDateCreation(currentDateTime);

        promotionRepo.save(promotion);

        // String insertPromotionQuery = "INSERT INTO T_PROMOTION (PRO_CODE, PRO_LIB, PRO_CREER_PAR, PRO_VERSION) VALUES (?, ?, ?, ?)";

        // // running insert query
        // jdbcTemplate.update(
        //         insertPromotionQuery, 
        //         promotion.getProCode(), 
        //         promotion.getProLib(), 
        //         promotion.getCreerPar(), 
        //         promotion.getProVersion()
        //     );

    }

    // function handling data update
    public Promotion updatePromoData(Integer proCode, String proLib, String proModifierPar) {

        // get current promotion version from repo function
        int currentVersion = promotionRepo.findProVersion(proCode);
        
        // get a new version by increment current by 1
        int newVersion = currentVersion + 1;
        
        
        // find data before update else throw error
        Promotion existingPromoData = promotionRepo.findById(proCode)
        .orElseThrow(() -> new IllegalArgumentException("Could not find data with given code"));
        
        // set new data then save ...
            existingPromoData.setProLib(proLib);
            existingPromoData.setModifierPar(proModifierPar);
            existingPromoData.setProVersion(newVersion);

            return promotionRepo.save(existingPromoData);


    }

    // function handling data deletion 
    public void deletePromoData(Integer proCode) {

        if(promotionRepo.existsById(proCode)) {
            promotionRepo.deleteById(proCode);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }


}
