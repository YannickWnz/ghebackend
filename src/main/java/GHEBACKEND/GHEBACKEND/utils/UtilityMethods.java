package GHEBACKEND.GHEBACKEND.utils;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class UtilityMethods {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // custom method to delete donnees referentielles
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

    // method getting current data version
    @SuppressWarnings("deprecation")
    public int getCurrentVersion(int code, String codeName, String tableName, String versionName) {

        String query = "SELECT " + versionName + " FROM " + tableName + " WHERE " + codeName + " = ?";

        return jdbcTemplate.queryForObject(query, new Object[]{code}, Integer.class);

    }

    // method validating user input string
    public static boolean validateInputString(String input, int minLength, int maxLength) {

        /**
         * Validation de l'input sur les bases suivantes:
         * - Not null
         * - Not empty
         * - Min and max length
         * - Ne doit contenir que des lettres et chiffres
         *
         * @param input le string a valider 
         * @param minLength la longueur minimum de l'input
         * @param maxLength la longueur maximum de l'input
         * @return true if valid, false otherwise
         */

        // Check if the input is null
        if (input == null) {
            System.out.println("Input is null");
            return false;
        }
        
        // Check if input is empty or only contains spaces
        if (input.trim().isEmpty()) {
            System.out.println("Input is empty");
            return false;
        }

        // Check input length
        if (input.length() < minLength || input.length() > maxLength) {
            System.out.println("Input length must be between " + minLength + " and " + maxLength + " characters.");
            return false;
        }

        // Check if input contains only letters and numbers
        // if (!input.matches("[a-zA-Z0-9]+")) {
        if (!input.matches("([A-Za-z0-9]+( [A-Za-z0-9]+)+)")) {
            System.out.println("Input must contain only letters and numbers.");
            return false;
        }

        // return true si aucune validation return false 
        return true;
    }



}
