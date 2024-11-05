package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_NATIONALITE")
public class NationaliteModel {

    @Id
    @Column(name = "NAT_CODE")
    private Integer nat_code;

    @Column(name = "NAT_LIB")
    private String nat_lib;

    @Column(name = "NAT_CREER_PAR")
    private String nat_creer_par;

    @Column(name = "NAT_MODIFIER_PAR")
    private String nat_modifier_par;

    @Column(name = "NAT_DATE_CREATION")
    private String nat_date_creation;

    @Column(name = "NAT_VERSION")
    private Integer nat_version;


    
    // getters
    public int getNatCode() {
        return nat_code;
    }

    public String getNatLib() {
        return nat_lib;
    }

    public String getNatModifierPar() {
        return nat_modifier_par;
    }

    public String getNatCreerPar() {
        return nat_creer_par;
    }

    public String getNatDateCreation() {
        return nat_date_creation;
    }

    public int getNatVersion() {
        return nat_version;
    }

    // setters
    public void setNatCode(int nat_code) {
        this.nat_code = nat_code;
    }

    public void setNatLib(String nat_lib) {
        this.nat_lib = nat_lib;
    }

    public void setNatModifierPar(String nat_modifier_par) {
        this.nat_modifier_par = nat_modifier_par;
    }

    public void setNatCreerPar(String nat_creer_par) {
        this.nat_creer_par = nat_creer_par;
    }

    public void setNatVersion(int nat_version) {
        this.nat_version = nat_version;
    }

    public void setNatDateCreation(String nat_date_creation) {
        this.nat_date_creation = nat_date_creation;
    }



}
