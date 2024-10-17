package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.AnneeAcademique;

@Repository
public interface AnneeAcademiqueRepo extends JpaRepository<AnneeAcademique, Integer> {

    // function fetching current version of data
    @Query(value = "SELECT t.AAC_VERSION FROM T_ANNEE_ACADEMIQUE t WHERE t.AAC_CODE = ?1", nativeQuery = true)
    Integer findProVersion(Integer aac_code);

}
