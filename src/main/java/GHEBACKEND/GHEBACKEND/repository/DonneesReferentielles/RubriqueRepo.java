package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;

@Repository
public interface RubriqueRepo extends JpaRepository<RubriqueModel, Integer> {

    @Modifying
    @Query(value = "UPDATE T_RUBRIQUE SET RUB_FRAIS_UNIQUE = ?2, RUB_MODIFIER_PAR = ?3 WHERE RUB_CODE = ?1", nativeQuery = true)
    void updateRubriqueFraisUnique(Integer code, boolean fraisUnique, String rubModifierPar);

}
