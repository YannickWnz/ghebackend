package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;

@Repository
public interface RubriqueRepo extends JpaRepository<RubriqueModel, Integer> {

    @Modifying
    @Query(value="UPDATE T_RUBRIQUE SET RUB_LIB = ?2, RUB_MODIFIER_PAR = ?3, RUB_VERSION = ?4 WHERE RUB_CODE = ?1)", nativeQuery=true)
    void updateRubriqueLib(Integer code, String rubLib, String rubModifierPar, Integer rubVersion);
    
    @Modifying
    @Query(value="UPDATE T_RUBRIQUE SET RUB_MONTANT = ?2, RUB_MODIFIER_PAR = ?3, RUB_VERSION = ?4 WHERE RUB_CODE = ?1)", nativeQuery=true)
    void updateRubriqueMontant(Integer code, String rubMontant, String rubModifierPar, Integer rubVersion);
    
    @Modifying
    @Query(value="UPDATE T_RUBRIQUE SET RUB_FRAIS_UNIQUE = ?2, RUB_MODIFIER_PAR = ?3, RUB_VERSION = ?4 WHERE RUB_CODE = ?1)", nativeQuery=true)
    void updateRubriqueFraisUnique(Integer code, String rubFraisUnique, String rubModifierPar, Integer rubVersion);

}
