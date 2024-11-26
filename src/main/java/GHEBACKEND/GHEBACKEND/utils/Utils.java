package GHEBACKEND.GHEBACKEND.utils;

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

}
