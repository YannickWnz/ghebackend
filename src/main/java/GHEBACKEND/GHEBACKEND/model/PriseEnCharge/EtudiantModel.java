package GHEBACKEND.GHEBACKEND.model.PriseEnCharge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "T_ETUDIANT")
public class EtudiantModel {

    @Id
    @Column(name = "ETD_CODE")
    private Integer etd_code;

    @Column(name = "ETD_PRENOM")
    private String etd_prenom;

    @Column(name = "ETD_NOM")
    private String etd_nom;

    @Column(name = "ETD_DATE_NAISSANCE")
    private String etd_date_naissance;

    @Column(name = "ETD_EMAIL")
    private String etd_email;

    @Column(name = "ETD_SEXE")
    private String etd_sexe;

    @Column(name = "ETD_ADDRESSE")
    private String etd_addresse;

    @Column(name = "ETD_PHONE")
    private String etd_phone;

    @Column(name = "ETD_CREER_PAR")
    private String etd_creer_par;

    @Column(name = "ETD_MODIFIER_PAR")
    private String etd_modifier_par;

    @Column(name = "ETD_VERSION")
    private Integer etd_version;

    @Column(name = "NAT_CODE")
    private Integer nat_code;

    // @OneToMany(cascade = CascadeType.ALL)
    // // private List<PersonneContactModel> contacts;
    // private List<PersonneContactModel> contacts = new ArrayList<>();

    
    // getters
    // public List<PersonneContactModel> getContacts() {
    //     return contacts;
    // }

    public Integer getEtdCode() {
        return etd_code;
    }

    public String getEtdPrenom() {
        return etd_prenom;
    }

    public String getEtdNom() {
        return etd_nom;
    }

    public String getEtdSexe() {
        return etd_sexe;
    }

    public String getEtdDateNaissance() {
        return etd_date_naissance;
    }

    public String getEtdEmail() {
        return etd_email;
    }

    public String getEtdAddresse() {
        return etd_addresse;
    }

    public String getEtdPhone() {
        return etd_phone;
    }

    public String getEtdCreerPar() {
        return etd_creer_par;
    }

    public String getEtdModifierPar() {
        return etd_modifier_par;
    }

    public Integer getNatCode() {
        return nat_code;
    }

    public Integer getEtdVersion() {
        return etd_version;
    }


    // setters

    // public void setContacts(List<PersonneContactModel> personneContactModel) {
    //     this.contacts = personneContactModel;
    // }

    public void setEtdCode(Integer etd_code) {
        this.etd_code = etd_code;
    }

    public void setEtdPrenom(String etd_prenom) {
        this.etd_prenom = etd_prenom;
    }

    public void setEtdNom(String etd_nom) {
        this.etd_nom = etd_nom;
    }

    public void setEtdSexe(String etd_sexe) {
        this.etd_sexe = etd_sexe;
    }

    public void setEtdEmail(String etd_email) {
        this.etd_email = etd_email;
    }

    public void setEtdDateNaissance(String etd_date_naissance) {
        this.etd_date_naissance = etd_date_naissance;
    }

    public void setEtdCreerPar(String etd_creer_par) {
        this.etd_creer_par = etd_creer_par;
    }

    public void setEtdModifierPar(String etd_modifier_par) {
        this.etd_modifier_par = etd_modifier_par;
    }

    public void setEtdAddresse(String etd_addresse) {
        this.etd_addresse = etd_addresse;
    }

    public void setEtdPhone(String etd_phone) {
        this.etd_phone = etd_phone;
    }

    public void setNatCode(Integer nat_code) {
        this.nat_code = nat_code;
    }

    public void setEtdVersion(Integer etd_version) {
        this.etd_version = etd_version;
    }

}
