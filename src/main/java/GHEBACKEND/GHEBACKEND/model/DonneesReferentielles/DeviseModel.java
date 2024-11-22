package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_DEVISE")
public class DeviseModel {

    @Id
    @Column(name="DEV_CODE")
    private Integer dev_code;

    @Column(name="DEV_LIB")
    private String dev_lib;

    @Column(name="DEV_STATUS")
    private boolean dev_status;

    @Column(name="DEV_CREER_PAR")
    private String dev_creer_par;

    @Column(name="DEV_MODIFIER_PAR")
    private String dev_modifier_par;

    @Column(name="DEV_DATE_CREATION")
    private String dev_date_creation;

    @Column(name="DEV_VERSION")
    private Integer dev_version;

    // GETTERS
    public Integer getDevCode() {
        return dev_code;
    }

    public String getDevLib() {
        return dev_lib;
    }

    public boolean getDevStatus() {
        return dev_status;
    }

    public String getDevCreerPar() {
        return dev_creer_par;
    }

    public String getDevModifierPar() {
        return dev_modifier_par;
    }

    public String getDevDateCreation() {
        return dev_date_creation;
    }

    public Integer getDevVersion() {
        return dev_version;
    }

    // SETTERS
    public void setDevCode(Integer dev_code) {
        this.dev_code = dev_code;
    }

    public void setDevLib(String dev_lib) {
        this.dev_lib = dev_lib;
    }

    public void setDevStatus(boolean dev_status) {
        this.dev_status = dev_status;
    }

    public void setDevCreerPar(String dev_creer_par) {
        this.dev_creer_par = dev_creer_par;
    }

    public void setDevModifierPar(String dev_modifier_par) {
        this.dev_modifier_par = dev_modifier_par;
    }

    public void setDevDateCreation(String dev_date_creation) {
        this.dev_date_creation = dev_date_creation;
    }

    public void setDevVersion(Integer dev_version) {
        this.dev_version = dev_version;
    }

}
