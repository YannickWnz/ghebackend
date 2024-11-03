package GHEBACKEND.GHEBACKEND.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


@Entity
@Table(name="T_UTILISATEURS")
public class User implements UserDetails{

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

    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implement roles/authorities if needed
        return null;
    }


    @Override
    public String getUsername() {
        return this.code;  // Use UTI_CODE as the username field
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




}
