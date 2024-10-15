package GHEBACKEND.GHEBACKEND.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UtilityMethods {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deleteDonneesRef(Integer code, String tableName, String codeName) {

        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + codeName + " = ?";

        jdbcTemplate.update(deleteQuery, code);

    }

}
