package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="T_SERVICE")
public class ServiceModel {

    @Id
    @Column(name="SCE_CODE")
    private Integer sce_code;

    @Column(name="DIR_CODE")
    private Integer dir_code;
    
    @Column(name="SCE_LIB")
    private String sce_lib;

    @Column(name="SCE_CREER_PAR")
    private String sce_creer_par;

    @Column(name="SCE_MODIFIER_PAR")
    private String sce_modifier_par;

    @Column(name="SCE_DATE_CREATION")
    private String sce_date_creation;

    @Column(name="SCE_VERSION")
    private Integer sce_version;


    // getters
    public Integer getSceCode() {
        return sce_code;
    }

    public Integer getDirCode() {
        return dir_code;
    }

    public String getSceLib() {
        return sce_lib;
    }

    public String getSceCreerPar() {
        return sce_creer_par;
    }

    public String getSceModifierPar() {
        return sce_modifier_par;
    }

    public String getSceDateCreation() {
        return sce_date_creation;
    }

    public Integer getSceVersion() {
        return sce_version;
    }

    // setters
    public void setSceCode(Integer sce_code) {
        this.sce_code = sce_code;
    }

    public void setDirCode(Integer dir_code) {
        this.dir_code = dir_code;
    }

    public void setSceLib(String sce_lib) {
        this.sce_lib = sce_lib;
    }

    public void setSceCreerPar(String sce_creer_par) {
        this.sce_creer_par = sce_creer_par;
    }

    public void setSceModifierPar(String sce_modifier_par) {
        this.sce_modifier_par = sce_modifier_par;
    }

    public void setSceDateCreation(String sce_date_creation) {
        this.sce_date_creation = sce_date_creation;
    }

    public void setSceVersion(Integer sce_version) {
        this.sce_version = sce_version;
    }



}
