# Documentation 

@GaiusYan
1. Documentation des utiliser dans utils :
Cette classe implemente toutes les méthodes complexes utiliser dans l'application, est la classe utilisée [Utils.java]("src/main/java/GHEBACKEND/GHEBACKEND/utils/Utils.java")

![Chemin de la classe]("https://github.com/GaiusYan/ghebackend/blob/dev-inscription/src/main/resources/images/Capture%20d%E2%80%99%C3%A9cran%202024-12-05%20111601.png")

2. Utilisation des methodes complexe :
    1. La fonction **incrementValueString**: 
    Cette fonction recupère incrémente une chaine de caractère, prend en paramètre un **code** et retourne le  **code + 1**

    ```java
        public static Integer incrementValue(String code){
            Integer value = 0;
            if(code != null && !code.isEmpty()){
                value = Integer.parseInt(code) + 1;
            }else
                value = 1;
            return value;
        }
    ```
   #### exemple : 
   ```java
        public static void main(String[] args) {
            String c = "22";
		    String code = incrementValue(c);//La valeur de code est 23
	    }

    ```

