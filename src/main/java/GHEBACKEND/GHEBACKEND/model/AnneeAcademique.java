package GHEBACKEND.GHEBACKEND.model;

import org.springframework.web.util.UriBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="T_ANNEE_ACADEMIQUE")
public class AnneeAcademique {

    @Id
    @Column(name="AAC_CODE")
    private int aac_code;

    @Column(name="AAC_LIB")
    private String aac_lib;

    @Column(name="AAC_CREER_PAR")
    private String aac_creer_par;

    @Column(name="AAC_MODIFER_PAR")
    private String aac_modifier_par;
    
    @Column(name="AAC_VERSION")
    private int aac_version;

    // getters
    
    public int getAacCode() {
        return aac_code;
    }
    
    public String getAacLib() {
        return aac_lib;
    }

    public String getAacCreerPar() {
        return aac_creer_par;
    }

    public String getAacModifierPar() {
        return aac_modifier_par;
    }

    public int getAacVersion() {
        return aac_version;
    }

    
    // setters
    public void setAacCode(int aac_code) {
        this.aac_code = aac_code;
    }

    public void setAacLib(String aac_lib) {
        this.aac_lib = aac_lib;
    }

    public void setAacCreerPar(String aac_creer_par) {
        this.aac_creer_par = aac_creer_par;
    }

    public void setAacModifierPar(String aac_modifier_par) {
        this.aac_modifier_par = aac_modifier_par;
    }

    public void setAacVersion(int aac_version) {
        this.aac_version = aac_version;
    }
    




    

}
