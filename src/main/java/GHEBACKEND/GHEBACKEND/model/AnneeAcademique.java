package GHEBACKEND.GHEBACKEND.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="T_ANNEE_ACADEMIQUE")
public class AnneeAcademique {

    @Id
    @Column(name="AAC_CODE")
    private int aac_code;

    @Column(name="AAC_LIB")
    private String aac_lib;

    @Column(name="AAC_CREER_PAR")
    private String aac_creer_par;

    @Column(name="AAC_MODIFER_PAR")
    private String aac_modifier_par;
    
    @Column(name="AAC_VERSION")
    private int aac_version;

    

}
