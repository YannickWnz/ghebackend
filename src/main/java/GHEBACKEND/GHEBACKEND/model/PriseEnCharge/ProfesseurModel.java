package GHEBACKEND.GHEBACKEND.model.PriseEnCharge;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.TypeProfesseur;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "T_PROFESSEUR")
@AllArgsConstructor
@NoArgsConstructor
public class ProfesseurModel {

    @Id
    @Column(name = "PRO_CODE", length = 8)
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

    public String getNomComplet(String[] args){
        return Utils.concatWithSpace(this.proNom, this.proPrenom);
    }
}
