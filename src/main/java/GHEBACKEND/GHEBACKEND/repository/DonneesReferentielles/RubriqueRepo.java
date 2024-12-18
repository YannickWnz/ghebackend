package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueDataProjection;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;

@Repository
public interface RubriqueRepo extends JpaRepository<RubriqueModel, Integer> {

    @Modifying
    @Query(value = "UPDATE T_RUBRIQUE SET RUB_FRAIS_UNIQUE = ?2, RUB_MODIFIER_PAR = ?3 WHERE RUB_CODE = ?1", nativeQuery = true)
    void updateRubriqueFraisUnique(Integer code, boolean fraisUnique, String rubModifierPar);

    @Query(value = "SELECT * FROM T_RUBRIQUE WHERE CLA_CODE = ?1 ", nativeQuery = true)
    List<RubriqueModel> recupererRubriquePourUneClasse(Integer code);

    @Query(value = "SELECT * FROM T_RUBRIQUE WHERE RUB_FRAIS_UNIQUE = 0 ", nativeQuery = true)
    List<RubriqueModel> getRubriqueNonObligatoire();

    // @Query(value = "SELECT RUB_CODE, RUB_LIB, RUB_MONTANT, RUB_FRAIS_UNIQUE, RUB_ORDRE_PAYMENT, CLA_LIB FROM T_RUBRIQUE RUB, T_CLASSE CLA WHERE RUB.CLA_CODE = CLA.CLA_CODE", nativeQuery = true)
    @Query(value = "SELECT RUB_CODE, RUB_LIB, RUB_MONTANT, RUB_FRAIS_UNIQUE, CLA_LIB FROM T_RUBRIQUE RUB, T_CLASSE CLA WHERE RUB.CLA_CODE = CLA.CLA_CODE", nativeQuery = true)
    List<RubriqueDataProjection> getRubriqueAndClasseData();

    //Cette méthode est tout simplement 'select * from t_rubrique_payer order by rub_frais_unique desc,rub_ordre asc'
    List<RubriqueModel> findByClasseOrderByRubFraisUniqueDescRubOrdreAsc(ClasseModel classe);

    //Cette méthode permet d'afficher la liste des rubriques qui ne sont obligatoires
    List<RubriqueModel> findByClasseAndRubFraisUniqueTrueOrderByRubFraisUniqueAscRubOrdreAsc(ClasseModel classe);
}
