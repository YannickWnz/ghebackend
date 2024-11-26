package GHEBACKEND.GHEBACKEND.model.PriseEnCharge;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.TypeProfesseur;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Transient;

/* 
 * Implémentation du model professeur
 */
@Data
@Builder
@Entity(name = "T_PROFESSEUR")
@AllArgsConstructor
@NoArgsConstructor
public class ProfesseurModel {

    @Id
    @Column(name = "PRO_CODE")
    private String proCode;
    @Column(name = "PRO_NOM", nullable = false)
    private String proNom;
    @Column(name = "PRO_PREN", nullable = true)
    private String proPrenom;
    @Column(name = "PRO_ADRESSE")
    private String proAdresse;
    @Column(name = "PRO_TEL")
    private String proTelephone;
    @ManyToOne
    @JoinColumn(name = "TPR_CODE", nullable = false)
    private TypeProfesseur typeProfesseur;
    @Transient
    private String name;
    
    /*  public String setName(String[] args){
        return Utils.concatWithSpace(this.proNom, this.proPrenom);
        } */
    public String getName(){
        return Utils.concatWithSpace(proNom, proPrenom);
    }

    public void setName(String name){
        this.name = name;
    }
}
