package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_CLASSE_MATIERE")
public class VolumeHoraire {

    @Id
    @Column(name="CLM_CODE")
    private Integer clm_code;
    
    @Column(name="CLM_VOLUME_HORAIRE")
    private Integer clm_volume_horaire;
    
    @Column(name="CLM_CREER_PAR")
    private String clm_creer_par;

    @Column(name="CLM_MODIFIER_PAR")
    private String clm_modifier_par;

    @Column(name="CLM_DATE_CREATION")
    private String clm_date_creation;

    @Column(name="CLM_VERSION")
    private Integer clm_version;
    
    @Column(name="CLA_CODE")
    private Integer cla_code;

    @Column(name="MAT_CODE")
    private Integer mat_code;


    // getters 
    public Integer getClmCode() {
        return clm_code;
    }
    
    public Integer getClmVolumeHoraire() {
        return clm_volume_horaire;
    }

    public String getClmCreerPar() {
        return clm_creer_par;
    }

    public String getClmModifierPar() {
        return clm_modifier_par;
    }

    public String getClmDateCreation() {
        return clm_date_creation;
    }

    public Integer getClaCode() {
        return cla_code;
    }

    public Integer getMatCode() {
        return mat_code;
    }

    public Integer getClmVersion() {
        return clm_version;
    }

    // setters
    public void setClmCode(Integer clm_code) {
        this.clm_code = clm_code;
    }

    public void setClmVolumeHoraire(Integer clm_volume_horaire) {
        this.clm_volume_horaire = clm_volume_horaire;
    }

    public void setClmCreerPar(String clm_creer_par) {
        this.clm_creer_par = clm_creer_par;
    }

    public void setClmModifierPar(String clm_modifier_par) {
        this.clm_modifier_par = clm_modifier_par;
    }

    public void setClmDateCreation(String clm_date_creation) {
        this.clm_date_creation = clm_date_creation;
    }

    public void setClaCode(Integer cla_code) {
        this.cla_code = cla_code;
    }

    public void setMatCode(Integer mat_code) {
        this.mat_code = mat_code;
    }

    public void setClmVersion(Integer clm_version) {
        this.clm_version = clm_version;
    }



}
