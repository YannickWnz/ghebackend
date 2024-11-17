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

    // Method generatrice de codes
    public Integer studentCodeGenerator() { 
        // code par default si aucune valeur n'existe dans la base de donnees
        int defaultCode = 1001;

        LocalDateTime now = LocalDateTime.now();

        /**
            I am working on method to generate unique student code in my spring boot app 
            the code need to be a combination of the current year eg: 2024 and a default number 1001
            so both number concatenated should give something like 20241001 for the very first student
            if another student is added we make select max sql query on the student table ... fetch the max then increment by to get 20241002 for the second student ... so on and so forth
            now if the current year change ... say we go from 2024 to 2025 ... i want the default number to go back to 1001

        */

        // int year = Calendar.getInstance().get(Calendar.YEAR);
        int year = now.getYear();

        int defaultNumber = 1001;

        // String number = Integer.toString(defaultNumber);

        // int a = Integer.parseInt(Integer.toString(9) + Integer.toString(10));


        int defaultStudentCode = Integer.parseInt(Integer.toString(year) + Integer.toString(defaultNumber)); 

        int defaultStudentCode1 = Integer.parseInt(Integer.toString(year) + Integer.toString(defaultNumber)) + 1; 
        

        // requete qui recupere la valeur max existante dans la base
        // String query = "SELECT MAX(" + codeName + ") FROM " + tableName;
    
        // Querying db
        // Integer maxCode = jdbcTemplate.queryForObject(query, Integer.class);
    
        // si aucune valeur n'est trouver ... defaultCode is returned ... else le max est returned apres incrementation by 1 
        // return (maxCode != null) ? (maxCode + 1) : defaultCode;
        // return defaultStudentCode;
        return defaultStudentCode;
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
