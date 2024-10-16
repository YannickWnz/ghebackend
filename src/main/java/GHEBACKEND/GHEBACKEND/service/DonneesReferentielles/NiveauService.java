package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Niveau;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.NiveauRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class NiveauService {

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NiveauRepo niveauRepo;

    public void addNewNiveau(Niveau niveau) {

        // utilityMethods.addDonneesRef(niveau.getNivLib(), niveau.getNivCreerPar(), "NIV_CODE", "T_NIVEAU");

        int code = utilityMethods.codeGenerator("NIV_CODE", "T_NIVEAU");

        Integer defaultVersion = 1;

        String insertPromotionQuery = "INSERT INTO T_NIVEAU (NIV_CODE, NIV_LIB, NIV_CREER_PAR, NIV_VERSION) VALUES (?, ?, ?, ?)";

        // running insert query
        jdbcTemplate.update(
                insertPromotionQuery, 
                code, 
                niveau.getNivLib(), 
                niveau.getNivCreerPar(),
                defaultVersion
            );

    }

}
