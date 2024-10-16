package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="T_NIVEAU")
public class Niveau {

    @Id
    @Column(name="NIV_CODE")
    private int niv_code;

    @Column(name="NIV_LIB")
    // @NotEmpty(message = "Libelle cannot be empty")
    // @Pattern(regexp = "[a-zA-Z0-9]")
    private String niv_lib;

    @Column(name="NIV_CREER_PAR")
    private String niv_creer_par;

    @Column(name="NIV_MODIFIER_PAR")
    private String niv_modifier_par;

    @Column(name="NIV_DATE_CREATION")
    private Date niv_date_creation;

    @Column(name="NIV_VERSION")
    private int niv_version;

    // getters
    public int getNivCode() {
        return niv_code;
    }

    public String getNivLib() {
        return niv_lib;
    }

    public String getNivModifierPar() {
        return niv_modifier_par;
    }

    public String getNivCreerPar() {
        return niv_creer_par;
    }

    public Date getNivDateCreation() {
        return niv_date_creation;
    }

    public int getNivVersion() {
        return niv_version;
    }

    // setters
    public void setNivCode(int niv_code) {
        this.niv_code = niv_code;
    }

    public void setNivLib(String niv_lib) {
        this.niv_lib = niv_lib;
    }

    public void setNivModifierPar(String niv_modifier_par) {
        this.niv_modifier_par = niv_modifier_par;
    }

    public void setNivCreerPar(String niv_creer_par) {
        this.niv_creer_par = niv_creer_par;
    }

    public void setNivVersion(int niv_version) {
        this.niv_version = niv_version;
    }

    public void setNivDateCreation(Date niv_date_creation) {
        this.niv_date_creation = niv_date_creation;
    }

}
