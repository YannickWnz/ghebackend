package GHEBACKEND.GHEBACKEND.model;

import jakarta.persistence.*;

@Entity
@Table(name="T_UTILISATEURS")
public class User {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="UTI_CODE")
    private String code;
    
    @Column(name="UTI_NOM")
    private String nom;
    
    @Column(name="UTI_PRENOM")
    private String prenom;
    
    @Column(name="UTI_PASSWORD")
    private String password;

    // Constructors, getters & setters 
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
