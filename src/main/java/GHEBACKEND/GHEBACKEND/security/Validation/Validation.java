package GHEBACKEND.GHEBACKEND.security.Validation;

import java.time.Instant;

import GHEBACKEND.GHEBACKEND.security.Utilisateur.Utilisateur;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name = "T_VALIDATION")
@AllArgsConstructor
@NoArgsConstructor
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Instant creation;
    private Instant expiration;
    private String code;
    @ManyToOne
    private Utilisateur utilisateur;
}
