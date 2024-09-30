package GHEBACKEND.GHEBACKEND.model;

import jakarta.persistence.*;

@Entity
@Table(name="T_UTILISATEURS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="UTI_CODE")
    private String code;
    
    @Column(name="UTI_NOM")
    private String nom;
    
    @Column(name="UTI_PRENOM")
    private String prenom;
    
    @Column(name="UTI_PASSWORD")
    private String password;

    // Constructors, getters & setters 
    private String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    private String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    private String getPrenom() {
        return prenom;
    }

    private void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }




}
