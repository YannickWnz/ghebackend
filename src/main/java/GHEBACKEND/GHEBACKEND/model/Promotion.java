package GHEBACKEND.GHEBACKEND.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="T_PROMOTION")
public class Promotion {

    @Id
    @Column(name="PRO_CODE")
    private int pro_code;

    @Column(name="PRO_LIB")
    private String pro_lib;

    @Column(name="PRO_CREER_PAR")
    private String pro_creer_par;
    
    @Column(name="PRO_MODIFIER_PAR")
    private String pro_modifier_par;

    @Column(name="PRO_VERSION")
    private String pro_version;

    public int getProCode() {
        return pro_code;
    }

    public void setProCode(int pro_code) {
        this.pro_code = pro_code;
    }

    public String getProLib() {
        return pro_lib;
    }

    public void setProLib(String pro_lib) {
        this.pro_lib = pro_lib;
    }

    public String getCreerPar() {
        return pro_creer_par;
    }

    public String getModifierPar() {
        return pro_modifier_par;
    }

    public String getProVersion() {
        return pro_version;
    }

    public void setProVersion(String pro_version) {
        this.pro_version = pro_version;
    }

    public void setCreerPar(String pro_creer_par) {
        this.pro_creer_par = pro_creer_par;
    }

    public void setModifierPar(String pro_modifier_par) {
        this.pro_modifier_par = pro_modifier_par;
    }

}
