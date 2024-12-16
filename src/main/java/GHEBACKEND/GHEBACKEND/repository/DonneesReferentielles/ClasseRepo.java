package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseDataProjection;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;



@Repository
public interface ClasseRepo extends JpaRepository<ClasseModel, Integer> {

    @Modifying
    @Query(value="UPDATE T_CLASSE SET CLA_LIB = ?2, CLA_MODIFIER_PAR = ?3, CLA_VERSION = ?4 WHERE CLA_CODE = ?1", nativeQuery=true)
    void updateClasseData(Integer code, String claLib, String claModifierPar, Integer claVersion);

    @Query(value="SELECT * FROM T_CLASSE WHERE NIV_CODE = ?1 AND FIL_CODE = ?2 ", nativeQuery=true)
    List<ClasseModel> getClasse(Integer nivCode, Integer filCode);

    @Query(value="SELECT CLA_CODE, CLA_LIB, FIL_LIB, NIV_LIB FROM T_CLASSE CLA, T_FILIERE FIL, T_NIVEAU NIV WHERE CLA.FIL_CODE = FIL.FIL_CODE AND CLA.NIV_CODE = NIV.NIV_CODE", nativeQuery=true)
    List<ClasseDataProjection> getClassesData();


}
