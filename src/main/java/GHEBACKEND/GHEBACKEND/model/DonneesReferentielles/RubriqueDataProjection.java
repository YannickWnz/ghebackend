package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import java.math.BigDecimal;

public interface RubriqueDataProjection {

    Integer getRubCode();     
    String getRubLib();     
    String getClaLib();
    BigDecimal getRubMontant();
    boolean getRubFraisUnique();
    // Integer getRubOrdrePaiement();

}
