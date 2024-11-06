package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="T_DIRECTION")
public class DirectionModel {

    @Id
    @Column(name="DIR_CODE")
    private Integer dir_code;

    @Column(name="DIR_LIB")
    private String dir_lib;

    @Column(name="DIR_CREER_PAR")
    private String dir_creer_par;

    @Column(name="DIR_MODIFIER_PAR")
    private String dir_modifier_par;

    @Column(name="DIR_DATE_CREATION")
    private String dir_date_creation;

    @Column(name="DIR_VERSION")
    private Integer dir_version;

    // GETTERS
    public Integer getDirCode() {
        return dir_code;
    }

    public String getDirLib() {
        return dir_lib;
    }

    public String getDirCreerPar() {
        return dir_creer_par;
    }

    public String getDirModifierPar() {
        return dir_modifier_par;
    }

    public String getDirDateCreation() {
        return dir_date_creation;
    }

    public Integer getDirVersion() {
        return dir_version;
    }

    // SETTERS
    public void setDirCode(Integer dir_code) {
        this.dir_code = dir_code;
    }

    public void setDirLib(String dir_lib) {
        this.dir_lib = dir_lib;
    }

    public void setDirCreerPar(String dir_creer_par) {
        this.dir_creer_par = dir_creer_par;
    }

    public void setDirModifierPar(String dir_modifier_par) {
        this.dir_modifier_par = dir_modifier_par;
    }

    public void setDirDateCreation(String dir_date_creation) {
        this.dir_date_creation = dir_date_creation;
    }

    public void setDirVersion(Integer dir_version) {
        this.dir_version = dir_version;
    }


}
