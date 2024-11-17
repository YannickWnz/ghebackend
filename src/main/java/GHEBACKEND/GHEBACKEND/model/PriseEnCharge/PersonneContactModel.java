package GHEBACKEND.GHEBACKEND.model.PriseEnCharge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_PERSONNE_CONTACT")
public class PersonneContactModel {

    
    @Id
    @Column(name = "CON_CODE")
    private Integer con_code;

    @Column(name = "CON_PRENOM")
    private String con_prenom;

    @Column(name = "CON_NOM")
    private String con_nom;

    @Column(name = "CON_EMAIL")
    private String con_email;

    @Column(name = "CON_ADDRESSE")
    private String con_addresse;

    @Column(name = "CON_PHONE")
    private String con_phone;

    @Column(name = "CON_CREER_PAR")
    private String con_creer_par;

    @Column(name = "CON_MODIFIER_PAR")
    private String con_modifier_par;

    @Column(name = "CON_VERSION")
    private Integer con_version;

    @Column(name = "LIE_CODE")
    private Integer lie_code;

    @ManyToOne
    // @JoinColumn(name = "student_code", nullable = false)
    private EtudiantModel etudiantModel; // Foreign key to T_ETUDIANT



    // getters
    public Integer getConCode() {
        return con_code;
    }

    public String getConPrenom() {
        return con_prenom;
    }

    public String getConNom() {
        return con_nom;
    }

    public String getConEmail() {
        return con_email;
    }

    public String getConAddresse() {
        return con_addresse;
    }

    public String getConPhone() {
        return con_phone;
    }

    public Integer getLieCode() {
        return lie_code;
    }

    public Integer getConVersion() {
        return con_version;
    }


    // setters
    public void seConCode(Integer con_code) {
        this.con_code = con_code;
    }

    public void setConPrenom(String con_prenom) {
        this.con_prenom = con_prenom;
    }

    public void setConNom(String con_nom) {
        this.con_nom = con_nom;
    }

    public void setConEmail(String con_email) {
        this.con_email = con_email;
    }

    public void setConAddresse(String con_addresse) {
        this.con_addresse = con_addresse;
    }

    public void setConPhone(String con_phone) {
        this.con_phone = con_phone;
    }

    public void setLieCode(Integer lie_code) {
        this.lie_code = lie_code;
    }

    public void setConVersion(Integer con_version) {
        this.con_version = con_version;
    }

}
