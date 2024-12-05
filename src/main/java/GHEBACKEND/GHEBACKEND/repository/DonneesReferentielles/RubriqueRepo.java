package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueDataProjection;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;

@Repository
public interface RubriqueRepo extends JpaRepository<RubriqueModel, Integer> {

    @Modifying
    @Query(value = "UPDATE T_RUBRIQUE SET RUB_FRAIS_UNIQUE = ?2, RUB_MODIFIER_PAR = ?3 WHERE RUB_CODE = ?1", nativeQuery = true)
    void updateRubriqueFraisUnique(Integer code, boolean fraisUnique, String rubModifierPar);

    @Query(value = "SELECT * FROM T_RUBRIQUE WHERE CLA_CODE = ?1 ", nativeQuery = true)
    List<RubriqueModel> recupererRubriquePourUneClasse(Integer code);

    @Query(value = "SELECT RUB_CODE, RUB_LIB, RUB_MONTANT, RUB_FRAIS_UNIQUE, CLA_LIB FROM T_RUBRIQUE RUB, T_CLASSE CLA WHERE RUB.CLA_CODE = CLA.CLA_CODE", nativeQuery = true)
    List<RubriqueDataProjection> getRubriqueAndClasseData();

}
