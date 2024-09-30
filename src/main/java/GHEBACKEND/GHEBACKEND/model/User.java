package GHEBACKEND.GHEBACKEND.model;

import jakarta.persistence.*;

@Entity
@Table(name="T_UTILISATEURS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    private String nom;

    private String prenom;
    
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

    


}
