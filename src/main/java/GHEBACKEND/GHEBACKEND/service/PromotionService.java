package GHEBACKEND.GHEBACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.Promotion;
import GHEBACKEND.GHEBACKEND.repository.PromotionRepo;

import java.util.*;

@Service
public class PromotionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PromotionRepo promotionRepo;

    // function fetching all promo ref data
    public List<Promotion> getAllPromoRefData() {

        List<Promotion> promoRefData = promotionRepo.findAll();
        
        return promotionRepo.findAll();
    }

    private int generateCode() {

        int code = 1001; 

        String findByIdQuery = "SELECT PRO_CODE FROM T_PROMOTION WHERE PRO_CODE = ?";

        jdbcTemplate.execute(findByIdQuery);



        return 1;
    }

    // service function inserting promotion donnees ref to db
    // @SuppressWarnings("deprecation")
    public void addPromotion(Promotion promotion) {

        int pro_code = 1001; // Default starting promotion code

        // Query to check if the promotion code exists
        String checkCodeQuery = "SELECT COUNT(*) FROM T_PROMOTION WHERE PRO_CODE = ?";

        // Loop to increment code if it already exists
        while (jdbcTemplate.queryForObject(checkCodeQuery, Integer.class, pro_code) > 0) {
            pro_code++;
        }

        // Set the unique promotion code to the promotion object
        promotion.setProCode(pro_code);

        String insertPromotionQuery = "INSERT INTO T_PROMOTION (PRO_CODE, PRO_LIB, PRO_CREER_PAR, PRO_VERSION) VALUES (?, ?, ?, ?)";

        // running insert query
        jdbcTemplate.update(
                insertPromotionQuery, 
                promotion.getProCode(), 
                promotion.getProLib(), 
                promotion.getCreerPar(), 
                promotion.getProVersion()
            );

    }
    

}
