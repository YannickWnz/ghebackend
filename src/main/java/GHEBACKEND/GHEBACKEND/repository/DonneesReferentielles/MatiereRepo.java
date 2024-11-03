package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.MatiereModel;

@Repository
public interface MatiereRepo extends JpaRepository<MatiereModel, Integer> {

    @Modifying
    @Query(value="UPDATE T_MATIERE SET MAT_LIB = ?2, MAT_MODIFIER_PAR = ?3, MAT_VERSION = ?4 WHERE MAT_CODE = ?1", nativeQuery=true)
    void updateMatiereData(Integer code, String matLib, String matModifierPar, Integer matVersion);

}
