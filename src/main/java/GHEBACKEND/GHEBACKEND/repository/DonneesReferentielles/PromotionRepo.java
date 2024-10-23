package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import java.util.List;

import org.springframework.data.jpa.repository.*;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Promotion;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;


@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Integer> {

    // function fetching current version of data
    @Query(value = "SELECT t.PRO_VERSION FROM T_PROMOTION t WHERE t.PRO_CODE = ?1", nativeQuery = true)
    Integer findProVersion(Integer pro_code);

    // function fetching current version of data
    // @Query(value = "SELECT t.PRO_LIB, t.PRO_CODE FROM T_PROMOTION t WHERE t.AAC_CODE = ?1", nativeQuery = true)
    @Query(value = "SELECT * FROM T_PROMOTION t WHERE t.AAC_CODE = ?1", nativeQuery = true)
    List<Promotion> getPromotionDataInAnneeAcademique(Integer aacCode);

}
