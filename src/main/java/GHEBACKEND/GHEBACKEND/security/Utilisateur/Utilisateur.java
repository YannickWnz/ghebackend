package GHEBACKEND.GHEBACKEND.security.Utilisateur;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import GHEBACKEND.GHEBACKEND.security.Role.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "T_UTILISATEUR")
public class Utilisateur implements UserDetails{
    @Id
    @Column(name = "UTI_CODE", nullable = false)
    private String utiCode;
    @Column(name = "UTI_NOM", nullable = true)
    private String utiNom;
    @Column(name = "UTI_PRENOM", nullable = true)
    private String utiPrenom;
    @Column(name = "UTI_PASSWORD", nullable = false)
    private String utiPassword;
    @Column(name = "UTI_EMAIL", nullable = true,unique = false)
    private String utiEmail;
    @Column(name = "UTI_USERNAME", nullable = false)
    private String utiUsername;
    @Column(name = "UTI_ACTIF", nullable = true)
    private boolean utiActif = false;
    @ManyToOne
    @JoinColumn(name = "ROLE_ID",nullable = true)
    private Role role;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public String getPassword() {
		return utiPassword;
	}
	@Override
	public String getUsername() {
		return utiUsername;
	}
	@Override
	public boolean isAccountNonExpired() {
		return utiActif;
	}
	@Override
	public boolean isAccountNonLocked() {
		return utiActif;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return utiActif;
	}
	@Override
	public boolean isEnabled() {
		return utiActif;
	}
}
