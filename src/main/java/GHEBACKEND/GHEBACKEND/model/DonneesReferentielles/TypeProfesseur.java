package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "T_TYPE_PROFESSEUR")
public class TypeProfesseur {

    @Id
    @Column(name="TPR_CODE")
    private Integer tpr_code;

    @Column(name="TPR_LIB")
    private String tpr_lib;

    @Column(name="TPR_TAUX_HORAIRE")
    private BigDecimal tpr_taux_horaire;

    @Column(name="TPR_CREER_PAR")
    private String tpr_creer_par;
    
    @Column(name="TPR_MODIFIER_PAR")
    private String tpr_modifier_par;

    @Column(name="TPR_DATE_CREATION")
    private String tpr_date_creation;

    @Column(name="TPR_VERSION")
    private Integer tpr_version;

    // getters
    public Integer getTprCode() {
        return tpr_code;
    }

    public String getTprLib() {
        return tpr_lib;
    }

    public BigDecimal getTprTauxHoraire() {
        return tpr_taux_horaire;
    }

    public String getTprCreerPar() {
        return tpr_creer_par;
    }

    public String getTprModifierPar() {
        return tpr_modifier_par;
    }

    public String getTprDateCreation() {
        return tpr_date_creation;
    }

    public Integer getTprVersion() {
        return tpr_version;
    }

    // setters
    public void setTprCode(Integer tpr_code) {
        this.tpr_code = tpr_code;
    }

    public void setTprLib(String tpr_lib) {
        this.tpr_lib = tpr_lib;
    }

    public void setTprTauxHoraire(BigDecimal tpr_taux_horaire) {
        this.tpr_taux_horaire = tpr_taux_horaire;
    }

    public void setTprCreerPar(String tpr_creer_par) {
        this.tpr_creer_par = tpr_creer_par;
    }

    public void setTprModifierPar(String tpr_modifier_par) {
        this.tpr_modifier_par = tpr_modifier_par;
    }

    public void setTprDateCreation(String tpr_date_creation) {
        this.tpr_date_creation = tpr_date_creation;
    }

    public void setTprVersion(Integer tpr_version) {
        this.tpr_version = tpr_version;
    }












}
