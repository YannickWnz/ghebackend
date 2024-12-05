package GHEBACKEND.GHEBACKEND.model.Inscription;

import java.time.LocalDate;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.AnneeAcademique;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Promotion;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
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

@Data
@Builder
@Entity
@Table(name = "T_INSCRIPTION")
@AllArgsConstructor
@NoArgsConstructor
public class Inscription {

    /* 
     * Test@GaiusYan
     */
    @Id
    @Column(name = "INS_CODE",nullable = false)
    private Integer insCode;
    @Column(name = "INS_DATE",nullable = false)
    private LocalDate insDate;
    @Column(name = "INS_NIVEAU_VALIDATION",nullable = false) 
    private Integer insNiveauValidation;
    @Column(name = "INS_CREER_PAR",nullable = true) 
    private String insCreerPar;
    @Column(name = "INS_MODIFIER_PAR",nullable = true) 
    private String insModifierPar;
    @Column(name = "INS_VERSION",nullable = false) 
    private int insVersion;
    @ManyToOne
    @JoinColumn(name = "ETD_CODE",nullable = true) 
    private EtudiantModel etudiant;
    @ManyToOne
    @JoinColumn(name = "PRO_CODE",nullable = true) 
    private Promotion promotion;
    @ManyToOne
    @JoinColumn(name = "AAC_CODE",nullable = true) 
    private AnneeAcademique anneeAcademique;
    @ManyToOne
    @JoinColumn(name = "CLA_CODE",nullable = true) 
    private ClasseModel classe;
}
