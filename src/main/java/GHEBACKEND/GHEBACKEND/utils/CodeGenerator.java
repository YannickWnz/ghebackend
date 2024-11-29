package GHEBACKEND.GHEBACKEND.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer generateCode(String tableName) {

        String checkQuery = "";
        int code = 1001;

        switch (tableName.toLowerCase()) {
            case "promotion":
            
           /*  int pro_code = 1001; */ // Default starting promotion code

            // Query to check if the promotion code exists
            // String checkCodeQuery = "SELECT COUNT(*) FROM T_PROMOTION WHERE PRO_CODE = ?";
            checkQuery = "SELECT COUNT(*) FROM T_PROMOTION WHERE PRO_CODE = ?";
    
            // Loop to increment code if it already exists
            while (jdbcTemplate.queryForObject(checkQuery, Integer.class, code) > 0) {
                code++;
            }

            return code;

            case "annee_academique":
            
            int aac_code = 1001; // Default starting promotion code

            // Query to check if the promotion code exists
            checkQuery = "SELECT COUNT(*) FROM T_ANNEE_ACADEMIQUE WHERE AAC_CODE = ?";
    
            // Loop to increment code if it already exists
            while (jdbcTemplate.queryForObject(checkQuery, Integer.class, code) > 0) {
                code++;
            }

            return code;

            default:
                throw new AssertionError();
        }

    }

}
