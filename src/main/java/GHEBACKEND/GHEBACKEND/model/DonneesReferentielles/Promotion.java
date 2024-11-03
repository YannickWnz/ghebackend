package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="T_PROMOTIONS")
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
    private Integer pro_version;

    // @Column(name="AAC_CODE")
    // private Integer pro_aac_code;

    @Column(name="PRO_DATE_CREATION")
    private String pro_date_creation;

    // getters & setters
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

    public String getProCreerPar() {
        return pro_creer_par;
    }

    public String getProModifierPar() {
        return pro_modifier_par;
    }

    public int getProVersion() {
        return pro_version;
    }

    public void setProVersion(int pro_version) {
        this.pro_version = pro_version;
    }

    public void setProCreerPar(String pro_creer_par) {
        this.pro_creer_par = pro_creer_par;
    }

    public void setProModifierPar(String pro_modifier_par) {
        this.pro_modifier_par = pro_modifier_par;
    }

    // public Integer getProAacCode() {
    //     return pro_aac_code;
    // }

    // public void setProAacCode(Integer pro_aac_code) {
    //     this.pro_aac_code = pro_aac_code;
    // }

    public void setProDateCreation(String pro_date_creation) {
        this.pro_date_creation = pro_date_creation;
    }

    public String getProDateCreation() {
        return pro_date_creation;
    }

}
