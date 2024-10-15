package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.*;



@Entity
@Table(name="T_FILIERE")
public class Filiere {

    @Id
    @Column(name="FIL_CODE")
    private int fil_code;

    @Column(name="FIL_LIB")
    // @NotEmpty(message = "Libelle cannot be empty")
    // @Pattern(regexp = "[a-zA-Z0-9]")
    private String fil_lib;

    @Column(name="FIL_CREER_PAR")
    private String fil_creer_par;

    @Column(name="FIL_MODIFIER_PAR")
    private String fil_modifier_par;

    @Column(name="FIL_DATE_CREATION")
    private Date fil_date_creation;

    @Column(name="FIL_VERSION")
    private int fil_version;

    // getters
    public int getFilCode() {
        return fil_code;
    }

    public String getFilLib() {
        return fil_lib;
    }

    public String getFilModifierPar() {
        return fil_modifier_par;
    }

    public String getFilCreerPar() {
        return fil_creer_par;
    }

    public Date getFilDateCreation() {
        return fil_date_creation;
    }

    public int getFilVersion() {
        return fil_version;
    }

    // setters
    public void setFilCode(int fil_code) {
        this.fil_code = fil_code;
    }

    public void setFilLib(String fil_lib) {
        this.fil_lib = fil_lib;
    }

    public void setFilModifierPar(String fil_modifier_par) {
        this.fil_modifier_par = fil_modifier_par;
    }

    public void setFilCreerPar(String fil_creer_par) {
        this.fil_creer_par = fil_creer_par;
    }

    public void setFilVersion(int fil_version) {
        this.fil_version = fil_version;
    }

    public void setFilDateCreation(Date fil_date_creation) {
        this.fil_date_creation = fil_date_creation;
    }

}
