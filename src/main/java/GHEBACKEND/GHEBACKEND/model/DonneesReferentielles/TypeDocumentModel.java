package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_TYPE_DOCUMENT")
public class TypeDocumentModel {

    @Id
    @Column(name="TDC_CODE")
    private int tdc_code;

    @Column(name="TDC_LIB")
    private String tdc_lib;

    @Column(name="TDC_CREER_PAR")
    private String tdc_creer_par;
    
    @Column(name="TDC_MODIFIER_PAR")
    private String tdc_modifier_par;

    @Column(name="TDC_VERSION")
    private Integer tdc_version;

    @Column(name="TDC_DATE_CREATION")
    private String tdc_date_creation;

    // getters & setters
    public int getTdcCode() {
        return tdc_code;
    }

    public void setTdcCode(int tdc_code) {
        this.tdc_code = tdc_code;
    }

    public String getTdcLib() {
        return tdc_lib;
    }

    public void setTdcLib(String tdc_lib) {
        this.tdc_lib = tdc_lib;
    }

    public String getTdcCreerPar() {
        return tdc_creer_par;
    }

    public String getTdcModifierPar() {
        return tdc_modifier_par;
    }

    public int getTdcVersion() {
        return tdc_version;
    }

    public void setTdcVersion(int tdc_version) {
        this.tdc_version = tdc_version;
    }

    public void setTdcCreerPar(String tdc_creer_par) {
        this.tdc_creer_par = tdc_creer_par;
    }

    public void setTdcModifierPar(String tdc_modifier_par) {
        this.tdc_modifier_par = tdc_modifier_par;
    }

    public void setTdcDateCreation(String tdc_date_creation) {
        this.tdc_date_creation = tdc_date_creation;
    }

    public String getTdcDateCreation() {
        return tdc_date_creation;
    }


}
