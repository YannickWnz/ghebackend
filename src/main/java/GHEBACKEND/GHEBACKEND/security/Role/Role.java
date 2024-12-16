package GHEBACKEND.GHEBACKEND.security.Role;


import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import GHEBACKEND.GHEBACKEND.security.Permission.Permission;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "T_ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_ID")
    private Integer rolId;
    @Column(name = "ROL_LIB",unique = true)
    private String rolLib;
    @Column(name = "ROL_NIVEAUVALIDATION")
    private Integer rolNiveauValidation;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Permission> permissions;  
    
    public Collection<? extends GrantedAuthority> getAuthorities(){
        final List<GrantedAuthority> grantedAuthorities = permissions.stream().map(
            permission -> new SimpleGrantedAuthority(permission.getFonctionnalite().getFncLib().toUpperCase())
        ).collect(Collectors.toList());
        grantedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", this.getRolLib().replace(" ", "_"))));
        return grantedAuthorities;
    } 
}
