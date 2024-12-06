package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="T_RUBRIQUE")
public class RubriqueModel {

    @Id
    @Column(name="RUB_CODE")
    private int rub_code;

    @Column(name="RUB_LIB")
    private String rub_lib;

    @Column(name="RUB_CREER_PAR")
    private String rub_creer_par;

    @Column(name="RUB_MODIFIER_PAR")
    private String rub_modifier_par;
    
    @Column(name="RUB_VERSION")
    private int rub_version;
    
    @Column(name="RUB_FRAIS_UNIQUE")
    private boolean rub_frais_unique;
    
    @Column(name="RUB_MONTANT")
    // private DecimalFormat rub_montant;
    private Double rub_montant;
    
    @Column(name="RUB_DATE_CREATION")
    private String rub_date_creation;
    
    @ManyToOne
    @JoinColumn(name="CLA_CODE")
    private ClasseModel classe;

    // getters
    public int getRubCode() {
        return rub_code;
    }
    
    public String getRubLib() {
        return rub_lib;
    }

    public String getRubCreerPar() {
        return rub_creer_par;
    }

    public String getRubModifierPar() {
        return rub_modifier_par;
    }

    public int getRubVersion() {
        return rub_version;
    }

    public boolean getRubFraisUnique() {
        return rub_frais_unique;
    }

    public Double getRubMontant() {
        return rub_montant;
    }

    public String getRubDateCreation() {
        return rub_date_creation;
    }

    public ClasseModel getClaCode() {
        return classe;
    }
    
    // setters
    public void setRubCode(int rub_code) {
        this.rub_code = rub_code;
    }

    public void setRubLib(String rub_lib) {
        this.rub_lib = rub_lib;
    }

    public void setRubCreerPar(String rub_creer_par) {
        this.rub_creer_par = rub_creer_par;
    }

    public void setRubModifierPar(String rub_modifier_par) {
        this.rub_modifier_par = rub_modifier_par;
    }

    public void setRubVersion(int rub_version) {
        this.rub_version = rub_version;
    }

    public void setRubFraisUnique(boolean rub_frais_unique) {
        this.rub_frais_unique = rub_frais_unique;
    }

    public void setRubMontant(Double rub_montant) {
        this.rub_montant = rub_montant;
    }

    public void setRubDateCreation(String rub_date_creation) {
        this.rub_date_creation = rub_date_creation;
    }

    public void setClaCode(ClasseModel cla_code) {
        this.classe = cla_code;
    }

}
