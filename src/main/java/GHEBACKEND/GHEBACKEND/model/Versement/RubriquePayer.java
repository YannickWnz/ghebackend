package GHEBACKEND.GHEBACKEND.model.Versement;

import java.time.LocalDate;
import org.springframework.data.annotation.Transient;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
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
@Table(name = "T_RUBRIQUE_PAYER")
public class RubriquePayer {
    @Id
    @Column(name = "RBP_CODE",nullable = false)
    private String rbpCode;
    @Column(name = "RBP_MONTANT",nullable = true)
    private Double rbpMontant;
    @Column(name = "RBP_DATE",nullable = true)
    private LocalDate rbpDate;
    @Column(name = "RBP_PREVU",nullable = true)
    private Double rbpPrevu;
    @Transient
    private Double rbpMontantRestant;
    @ManyToOne
    @JoinColumn(name = "RUB_CODE",nullable = false)
    private RubriqueModel rubrique;

    @ManyToOne
    @JoinColumn(name = "INS_CODE",nullable = false)
    private Inscription inscription;

    public Double getRbpMontantRestant(){
        return this.rbpMontantRestant;
    }

    public void setRbpMontantRestant(Double rbpMontantRestant){
        this.rbpMontantRestant = this.rbpPrevu - this.rbpMontant;
    }
}