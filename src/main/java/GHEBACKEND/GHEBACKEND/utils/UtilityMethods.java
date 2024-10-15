package GHEBACKEND.GHEBACKEND.utils;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class UtilityMethods {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deleteDonneesRef(Integer code, String tableName, String codeName) {

        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + codeName + " = ?";

        jdbcTemplate.update(deleteQuery, code);

    }

    // Method generatrice de codes
    public Integer codeGenerator(String codeName, String tableName) { 
        // code par default si aucune valeur n'existe dans la base de donnees
        int defaultCode = 1001;
    
        // requete qui recupere la valeur max existante dans la base
        String query = "SELECT MAX(" + codeName + ") FROM " + tableName;
    
        // Querying db
        Integer maxCode = jdbcTemplate.queryForObject(query, Integer.class);
    
        // si aucune valeur n'est trouver ... defaultCode is returned ... else le max est returned apres incrementation by 1 
        return (maxCode != null) ? (maxCode + 1) : defaultCode;
    }

    // utility methods qui ajoute les donnees aux tables qui ont les champs suivant (code, lib, creerPar, modifierPar, dateCreate, version)
    public void addDonneesRef(String lib, String creerPar, String codeName, String tableName) {

        // getting code from codeGenerator method
        Integer Code = codeGenerator("FIL_CODE", "T_FILIERE");

        // version set to 1 by default on creation
        Integer version = 1;
        
        String query = "INSERT INTO " + tableName + " (FIL_CODE, FIL_LIB, FIL_CREER_PAR, FIL_VERSION) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(
            query,
            Code,
            lib,
            creerPar,
            version
        );

    }

    // method qui se charge de la recuperation de la date et heure actuelle
    public String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Return the formatted date and time as a string
        return currentDateTime.format(formatter);
    }



}
