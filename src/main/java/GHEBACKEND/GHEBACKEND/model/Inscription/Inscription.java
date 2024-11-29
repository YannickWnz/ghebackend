package GHEBACKEND.GHEBACKEND.model.Inscription;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.AnneeAcademique;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Promotion;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "T_INSCRIPTION")
@AllArgsConstructor
@NoArgsConstructor
public class Inscription {
    @Id
    @Column(name = "INS_CODE",nullable = false)
    private int insCode;
    @Column(name = "INS_CODE",nullable = false)
    private LocalDate insDate;
    @Column(name = "INS_CODE",nullable = false) 
    private int insNiveauValidation;
    @Column(name = "INS_CODE",nullable = true) 
    private String insCreerPar;
    @Column(name = "INS_CODE",nullable = true) 
    private String insModifierPar;
    @Column(name = "INS_CODE",nullable = false) 
    private int insVersion;
    @ManyToAny
    @JoinColumn(name = "ETD_CODE",nullable = false) 
    private EtudiantModel etudiant;
    @ManyToAny
    @Column(name = "PRO_CODE",nullable = true) 
    private Promotion promotion;
    @ManyToAny
    @Column(name = "AAC_CODE",nullable = true) 
    private AnneeAcademique anneeAcademique;
    @ManyToAny
    @Column(name = "CLA_CODE",nullable = true) 
    private ClasseModel classe;
}
