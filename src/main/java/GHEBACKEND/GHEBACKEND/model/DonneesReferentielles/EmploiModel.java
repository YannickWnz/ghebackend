package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_EMPLOI")
public class EmploiModel {

    @Id
    @Column(name = "EMP_CODE")
    private Integer emp_code;

    @Column(name = "EMP_LIB")
    private String emp_lib;

    @Column(name = "EMP_CREER_PAR")
    private String emp_creer_par;

    @Column(name = "EMP_MODIFIER_PAR")
    private String emp_modifier_par;

    @Column(name = "EMP_DATE_CREATION")
    private String emp_data_creation;

    @Column(name = "EMP_VERSION")
    private Integer emp_version;

    // getters
    public Integer getEmpCode() {
        return emp_code;
    }

    public String getEmpLib() {
        return emp_lib;
    }

    public String getEmpCreerPar() {
        return emp_creer_par;
    }

    public String getEmpModifierPar() {
        return emp_modifier_par;
    }

    public String getEmpDateCreation() {
        return emp_data_creation;
    }

    public Integer getEmpVersion() {
        return emp_version;
    }

    // setters
    public void setEmpCode(Integer emp_code) {
        this.emp_code = emp_code;
    }

    public void setEmpLib(String emp_lib) {
        this.emp_lib = emp_lib;
    }

    public void setEmpCreerPar(String emp_creer_par) {
        this.emp_creer_par = emp_creer_par;
    }

    public void setEmpModifierPar(String emp_modifier_par) {
        this.emp_modifier_par = emp_modifier_par;
    }

    public void setEmpVersion(Integer emp_version) {
        this.emp_version = emp_version;
    }

}
