package GHEBACKEND.GHEBACKEND.service.Inscription;

import java.time.LocalDate;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.AnneeAcademique;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Promotion;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/* 
 * Test
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InscriptionRequest {
    private LocalDate insDate;
    private String insNiveauValidation;
    private String insModifierPar;
    private String insCreerPar;
    private Integer insVersion;
    private EtudiantModel etudiant;
    private Promotion promotion;
    private AnneeAcademique annee;
    private ClasseModel classe;
}
