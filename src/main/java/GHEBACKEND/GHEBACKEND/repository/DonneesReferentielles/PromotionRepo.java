package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Promotion;


@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Integer> {

    // function fetching current version of data
    @Query(value = "SELECT t.PRO_VERSION FROM T_PROMOTIONS t WHERE t.PRO_CODE = ?1", nativeQuery = true)
    Integer findProVersion(Integer pro_code);
    // // function fetching current version of data
    // @Query(value = "SELECT t.PRO_VERSION FROM T_PROMOTION t WHERE t.PRO_CODE = ?1", nativeQuery = true)
    // Integer findProVersion(Integer pro_code);

    // function fetching current version of data
    // @Query(value = "SELECT t.PRO_LIB, t.PRO_CODE FROM T_PROMOTION t WHERE t.AAC_CODE = ?1", nativeQuery = true)
    // @Query(value = "SELECT * FROM T_PROMOTION t WHERE t.AAC_CODE = ?1", nativeQuery = true)
    // List<Promotion> getPromotionDataInAnneeAcademique(Integer aacCode);

}
