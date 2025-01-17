package GHEBACKEND.GHEBACKEND.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


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
    // function qui se charge de la recuperation de la version courant d'une donnee referentielle
    @SuppressWarnings("deprecation")
    public int getCurrentVersion(int code, String codeName, String tableName, String versionName) {

        /**
         * parametre utiliser pour la recuperation
         * - versionName; (nom de la colonne version dans la table Ex: FIL_VERSION pour la table T_FILIERE, PRO_VERSION pour la talbe T_PROMOTION etc ...)
         * - tableName (nom de la table Ex: T_PROMOTION, T_FILIERE ...)
         * - codeName (nom de la colonne code Ex: PRO_CODE pour la table T_PROMOTION, AAC_CODE pour la table T_ANNEE_ACADEMIQUE, FIL_CODE ...)
         */
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
        // if (!input.matches("([A-Za-z0-9]+( [A-Za-z0-9]+)+)")) {
        if (!input.matches("^[a-zA-Z0-9\\s]*$")) {
            System.out.println("Input must contain only letters and numbers.");
            return false;
        }

        // return true si aucune validation return false 
        return true;
    }

    // methode de validation des codes avant de faire une requete dans la database
    public static boolean validateInputCode(String code) {

        /**
         * Validation de l'input sur les bases suivantes:
         * - Not null
         * - Ne doit contenir  chiffres
         *
         * @param code le code a valider
         * @return true if valid, false otherwise
         */

        // Check if the input is null
        if (code == null) {
            System.out.println("Code cannot be null");
            return false;
        }
        
        // Check if input has a maximum length of 5 and is numeric
        if (!code.matches("^\\d{1,5}$")) {
            System.out.println("Input must contain only letters and numbers.");
            return false;
        }

        // return true si aucune validation return false 
        return true;
    }

    public Integer getTotalNumberOfDonneesRef(String tableName) {

                // requete qui recupere le total count des donnees dans la table passee en param
                String query = "SELECT COUNT(*) FROM " + tableName;
    
                // Querying db
                Integer totalCount = jdbcTemplate.queryForObject(query, Integer.class);

                return totalCount;
                
    }



}
