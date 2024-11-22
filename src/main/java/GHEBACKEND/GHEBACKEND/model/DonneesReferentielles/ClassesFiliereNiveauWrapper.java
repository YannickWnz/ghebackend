package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

public class ClassesFiliereNiveauWrapper {

    private Integer cla_code;
    private String cla_lib;
    private String fil_lib;
    private String niv_lib;

    public Integer getClaCode() {
        return cla_code;
    }

    public String getClaLib() {
        return cla_lib;
    }

    public String getFilLib() {
        return fil_lib;
    }

    public String getNivLib() {
        return niv_lib;
    }

    public void setClaCode(Integer cla_code) {
        this.cla_code = cla_code;
    }

    public void setClaLib(String cla_lib) {
        this.cla_lib = cla_lib;
    }

    public void setFilLib(String fil_lib) {
        this.fil_lib = fil_lib;
    }

    public void setNivLib(String niv_lib) {
        this.niv_lib = niv_lib;
    }

    

}
