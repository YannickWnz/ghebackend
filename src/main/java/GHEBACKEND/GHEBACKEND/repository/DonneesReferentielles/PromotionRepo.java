package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.*;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Promotion;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;


@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Integer> {

    // function fetching current version of data
    @Query(value = "SELECT t.PRO_VERSION FROM T_PROMOTION t WHERE t.PRO_CODE = ?1", nativeQuery = true)
    Integer findProVersion(Integer pro_code);

}
