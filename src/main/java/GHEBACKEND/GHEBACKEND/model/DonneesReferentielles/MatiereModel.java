package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="T_MATIERE")
public class MatiereModel {

    @Id
    @Column(name="MAT_CODE")
    private int mat_code;

    @Column(name="MAT_LIB")
    private String mat_lib;

    @Column(name="MAT_CREER_PAR")
    private String mat_creer_par;

    @Column(name="MAT_MODIFIER_PAR")
    private String mat_modifier_par;

    @Column(name="MAT_DATE_CREATION")
    private String mat_date_creation;

    @Column(name="MAT_VERSION")
    private int mat_version;

    // getters
    public int getMatCode() {
        return mat_code;
    }

    public String getMatLib() {
        return mat_lib;
    }

    public String getMatModifierPar() {
        return mat_modifier_par;
    }

    public String getMatCreerPar() {
        return mat_creer_par;
    }

    public String getMatDateCreation() {
        return mat_date_creation;
    }

    public int getMatVersion() {
        return mat_version;
    }

    // setters
    public void setMatCode(int mat_code) {
        this.mat_code = mat_code;
    }

    public void setMatLib(String mat_lib) {
        this.mat_lib = mat_lib;
    }

    public void setMatModifierPar(String mat_modifier_par) {
        this.mat_modifier_par = mat_modifier_par;
    }

    public void setMatCreerPar(String mat_creer_par) {
        this.mat_creer_par = mat_creer_par;
    }

    public void setMatVersion(int mat_version) {
        this.mat_version = mat_version;
    }

    public void setMatDateCreation(String mat_date_creation) {
        this.mat_date_creation = mat_date_creation;
    }

}
