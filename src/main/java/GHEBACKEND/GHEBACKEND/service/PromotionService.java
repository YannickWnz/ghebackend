package GHEBACKEND.GHEBACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.Promotion;

@Service
public class PromotionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addPromotion(Promotion promotion) {

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
