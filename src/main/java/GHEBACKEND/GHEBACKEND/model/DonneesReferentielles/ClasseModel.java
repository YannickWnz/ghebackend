package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="T_CLASSE")
public class ClasseModel {

    @Id
    @Column(name="CLA_CODE")
    private int cla_code;

    @Column(name="CLA_LIB")
    private String cla_lib;

    @Column(name="CLA_CREER_PAR")
    private String cla_creer_par;

    @Column(name="CLA_MODIFIER_PAR")
    private String cla_modifier_par;

    @Column(name="CLA_DATE_CREATION")
    private String cla_date_creation;

    @Column(name="CLA_VERSION")
    private int cla_version;

    @Column(name="FIL_CODE")
    private int cla_fil_code;

    @Column(name="NIV_CODE")
    private int cla_niv_code;

    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RubriqueModel> rubrique;

/*     // getters
    public int getClaCode() {
        return cla_code;
    }

    public String getClaLib() {
        return cla_lib;
    }

    public String getClaModifierPar() {
        return cla_modifier_par;
    }

    public String getClaCreerPar() {
        return cla_creer_par;
    }

    public String getClaDateCreation() {
        return cla_date_creation;
    }

    public int getClaVersion() {
        return cla_version;
    }

    public int getClaFilCode() {
        return cla_fil_code;
    }

    public int getClaNivCode() {
        return cla_niv_code;
    }

    // setters
    public void setClaCode(int cla_code) {
        this.cla_code = cla_code;
    }

    public void setClaLib(String cla_lib) {
        this.cla_lib = cla_lib;
    }

    public void setClaModifierPar(String cla_modifier_par) {
        this.cla_modifier_par = cla_modifier_par;
    }

    public void setClaCreerPar(String cla_creer_par) {
        this.cla_creer_par = cla_creer_par;
    }

    public void setClaVersion(int cla_version) {
        this.cla_version = cla_version;
    }

    public void setClaDateCreation(String cla_date_creation) {
        this.cla_date_creation = cla_date_creation;
    }

    public void setClaFilCode(int cla_fil_code) {
        this.cla_fil_code = cla_fil_code;
    }

    public void setClaNivCode(int cla_niv_code) {
        this.cla_niv_code = cla_niv_code;
    } */

}
