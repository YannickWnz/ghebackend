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
    public static boolean dateEquales(LocalDate localDate1, LocalDate localDate2){ return localDate1.equals(localDate2);}
    /* 
     * Aucune description le nom de la fonction est déjà intituive
     */
    public static boolean isInferieur(LocalDate localDate1, LocalDate localDate2){return localDate1.isAfter(localDate2);}
    public static boolean isSuperieur(LocalDate localDate1, LocalDate localDate2){return localDate1.isBefore(localDate2);}
}
