package GHEBACKEND.GHEBACKEND.security.Fonctionnalite;


import java.util.List;

import org.hibernate.generator.values.internal.GeneratedValuesImpl;

import GHEBACKEND.GHEBACKEND.security.Permission.Permission;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "T_FONCTIONNALITE")
@AllArgsConstructor
@NoArgsConstructor
public class Fonctionnalite {
    @Id 
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "FNC_CODE")
    private Integer fncCode;
    @Column(name = "FNC_LIB")
    private String fncLib;
    @Column(name = "FNC_DESCRIPTION")
    private String fncDescription;
    @OneToMany
    private List<Permission> permissions;
}
