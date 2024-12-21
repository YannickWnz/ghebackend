package GHEBACKEND.GHEBACKEND.model.Inscription;

import java.time.LocalDate;

import org.hibernate.annotations.Collate;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "T_HISTORIQUE_RUB_INS")
@AllArgsConstructor
@NoArgsConstructor
/* 
 * Cette table permet de récuperer et insérer toutes les rubriques possibles liées à cette classe
 */
public class HistoriqueRubriqueInscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HIS_CODE")
    private Integer hisCode;
    @Column(name = "HIS_DATE_CREATION")
    private LocalDate hisDateCreation;
    @Column(name = "HIS_RUB_LIB")
    private String hisRubLib;
    @Column(name = "HIS_MONTANT_PREVU")
    private Double hisMontantPrevu;
    @Column(name = "HIS_RUB_ORDRE")
    private Integer hisRubOrdre;
    @Column(name = "HIS_FRAIS_UNIQUE")
    private boolean hisFraisUnique;
    @ManyToOne
    @JoinColumn(name = "HIS_INS_CODE",nullable = false)
    private Inscription inscription;
    @ManyToOne
    @JoinColumn(name = "HIS_RUB_CODE",nullable = false)
    private RubriqueModel rubrique;
}
