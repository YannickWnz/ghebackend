package GHEBACKEND.GHEBACKEND.security.Permission;

import java.util.List;

import GHEBACKEND.GHEBACKEND.security.Fonctionnalite.Fonctionnalite;
import GHEBACKEND.GHEBACKEND.security.Role.Role;
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
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_PERMISSION")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer perId;
    @ManyToOne
    @JoinColumn(name = "ROL_ID")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "FNC_CODE")
    private Fonctionnalite fonctionnalite;
}
