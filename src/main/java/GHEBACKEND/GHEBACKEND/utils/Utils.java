package GHEBACKEND.GHEBACKEND.utils;

import java.time.LocalDate;
import java.time.LocalTime;

/* 
 * Cette classe abstraite est créée afin d'implementer toutes les méthodes aux quelles 
 * les développeurs ont besoin pour utiliser dans le programme
 * @GaiusYan
 */
public abstract class Utils {

    /* Cette méthode permet de concatener avec
     * des espaces deux chaines de caractères
     * @GaiusYan
     */
    public static String concatWithSpace(String str1, String str2){
        if(str1 == null) str1 = ""; 
        if(str2 == null) str2 = ""; 

        return str1+ " " + str2;
    }

     /* 
     * Cette fonction permet de retourner la date actuelle
     * @GaiusYan
     */

    public static LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    /* 
     * Cette fonction permet de retourner l'heure actuelle
     * @GaiusYan
     */
    public static LocalTime getCurrentTime(){
        return LocalTime.now();
    }

    /* 
     * Comparation des dates
     */

     /* 
      * Cette fonction permet de determiner si les deux dates sont équals
      @GaiusYan
      */
    public static boolean isEqual(LocalDate localDate1, LocalDate localDate2){ return localDate1.equals(localDate2);}
    /* 
     * Aucune description le nom de la fonction est déjà intituive
     * @GaiusYan
     */
    public static boolean isInferieur(LocalDate localDate1, LocalDate localDate2){return localDate1.isAfter(localDate2);}
    
    public static boolean isSuperieur(LocalDate localDate1, LocalDate localDate2){return localDate1.isBefore(localDate2);}

    /* 
     * Cette fonction permet d'incrementer un string 
     * Exemple : 
     * incrementeValue("1") == 2
     * @GaiusYan
     */
    public static Integer incrementValue(String code){
        Integer value = 0;
        if(code != null && !code.isEmpty()){
            value = Integer.parseInt(code) + 1;
        }else
            value = 1;
        return value;
    }


    /* 
     * Cette fonction permet de formatter un string
     * Par exemple : 
     * formatString(1) retourne 00001;
     * @GaiusYan
     */
    public static String formatString(String value){
        return String.format("%05d", Integer.parseInt(value));
    }
}