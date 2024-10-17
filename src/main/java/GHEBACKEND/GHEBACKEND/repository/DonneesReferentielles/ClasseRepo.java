package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;

@Repository
public interface ClasseRepo extends JpaRepository<ClasseModel, Integer> {

    @Modifying
    @Query(value="UPDATE T_CLASSE SET CLA_LIB = ?2, CLA_MODIFIER_PAR = ?3, CLA_VERSION = ?4 WHERE CLA_CODE = ?1", nativeQuery=true)
    void updateClasseData(Integer code, String claLib, String claModifierPar, Integer claVersion);

}
