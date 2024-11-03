package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="T_LIEN")
public class LienModel {

    @Id
    @Column(name="LIE_CODE")
    private Integer lie_code;

    @Column(name="LIE_LIB")
    private String lie_lib;

    @Column(name="LIE_CREER_PAR")
    private String lie_creer_par;

    @Column(name="LIE_MODIFIER_PAR")
    private String lie_modifier_par;

    @Column(name="LIE_DATE_CREATION")
    private String lie_date_creation;

    @Column(name="LIE_VERSION")
    private Integer lie_version;

    // getters
    public Integer getLieCode() {
        return lie_code;
    }

    public String getLieLib() {
        return lie_lib;
    }

    public String getLieCreerPar() {
        return lie_creer_par;
    }

    public String getLieModifierPar() {
        return lie_modifier_par;
    }

    public String getLieDateCreation() {
        return lie_date_creation;
    }

    public Integer getLieVersion() {
        return lie_version;
    }

    // setters
    public void setLieCode(Integer lie_code) {
        this.lie_code = lie_code;
    }

    public void setLieLib(String lie_lib) {
        this.lie_lib = lie_lib;
    }

    public void setLieCreerPar(String lie_creer_par) {
        this.lie_creer_par = lie_creer_par;
    }

    public void setLieModifierPar(String lie_modifier_par) {
        this.lie_modifier_par = lie_modifier_par;
    }

    public void setLieDateCreation(String lie_date_creation) {
        this.lie_date_creation = lie_date_creation;
    }

    public void setLieVersion(Integer lie_version) {
        this.lie_version = lie_version;
    }


}
