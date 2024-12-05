package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

public class ClassesDTO {

    private int claCode;       // CLA_CODE from T_CLASSE
    private String claLib;     // CLA_LIB from T_CLASSE
    private String filLib;     // FIL_LIB from T_FILIERE
    private String nivLib;     // NIV_LIB from T_NIVEAU

    // Constructor
    public ClassesDTO(int claCode, String claLib, String filLib, String nivLib) {
        this.claCode = claCode;
        this.claLib = claLib;
        this.filLib = filLib;
        this.nivLib = nivLib;
    }

    // Getters and Setters
    public int getClaCode() {
        return claCode;
    }

    public void setClaCode(int claCode) {
        this.claCode = claCode;
    }

    public String getClaLib() {
        return claLib;
    }

    public void setClaLib(String claLib) {
        this.claLib = claLib;
    }

    public String getFilLib() {
        return filLib;
    }

    public void setFilLib(String filLib) {
        this.filLib = filLib;
    }

    public String getNivLib() {
        return nivLib;
    }

    public void setNivLib(String nivLib) {
        this.nivLib = nivLib;
    }


}
