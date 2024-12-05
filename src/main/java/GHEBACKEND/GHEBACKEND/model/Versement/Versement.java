package GHEBACKEND.GHEBACKEND.model.Versement;

import java.time.LocalDate;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Table(name = "T_VERSEMENT")
public class Versement {
    @Id
    @GeneratedValue
    @Column(name = "VER_CODE",nullable = false)
    private String verCode;
    @Column(name = "VER_MONTANT",nullable = false)
    private Double verMontant;
    @Column(name = "VER_DATE",nullable = false)
    private LocalDate verDate;
    @ManyToOne
    @JoinColumn(name = "INS_CODE",nullable = false)
    private Inscription inscription;
}
