package GHEBACKEND.GHEBACKEND.model.PriseEnCharge;

import java.time.LocalDate;
import java.time.LocalDateTime;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.DirectionModel;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.EmploiModel;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ServiceModel;
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
@Table(name = "T_PERSONNEL")
@AllArgsConstructor
@NoArgsConstructor
public class PersonnelModel {

    @Id
    @Column(name = "PER_CODE")
    private Integer perCode;
    @Column(name = "PER_NOM", nullable =  false)
    private String perNom;
    @Column(name = "PER_PRENOM", nullable =  false)
    private String perPrenom;
    @Column(name = "PER_SEXE", nullable =  false)
    private String perSexe;
    @Column(name = "PER_DATE_DE_NAISSANCE", nullable =  false)
    private LocalDate perDateNais;
    @Column(name = "PER_ADDRESSE", nullable =  false)
    private String perAdresse;
    @Column(name = "PER_EMAIL", nullable =  false)
    private String perEmail;
    @Column(name = "PER_TELEPHONE", nullable =  false)
    private String perTelephone;
    @Column(name = "PER_CREER_PAR", nullable =  false)
    private String perCreerPar;
    @Column(name = "PER_MODIFIER_PAR", nullable =  false)
    private String perModifierPar;
    @Column(name = "PER_DATE_CREATION", nullable =  false)
    private LocalDateTime perDateCreation;
    private String perVersion;
    @ManyToOne
    @JoinColumn(name = "EMP_CODE",nullable = false)
    private EmploiModel emploi;
    @ManyToOne
    @JoinColumn(name = "SCE_CODE",nullable = false)
    private ServiceModel service;
    @ManyToOne
    @JoinColumn(name = "DIR_CODE",nullable = false)
    private DirectionModel direction;
}
