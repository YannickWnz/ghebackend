package GHEBACKEND.GHEBACKEND.repository.PriseEnCharge;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.StudentInscriptionDetailsProjection;

@Repository
public interface EtudiantRepo extends JpaRepository<EtudiantModel, Integer> {
    
    @Query(value="SELECT * FROM T_ETUDIANT WHERE ETD_CODE = ?1", nativeQuery=true)
    Optional<EtudiantModel> getStudentByCode(Integer code);

    @Query(value=" SELECT AAC_LIB, CLA_LIB, PRO_LIB, FIL_LIB, NIV_LIB FROM T_ANNEE_ACADEMIQUE AAC, T_PROMOTION PRO, T_CLASSE CLA, T_INSCRIPTION INS, T_FILIERE FIL, T_NIVEAU NIV  WHERE INS.ETD_CODE = ?1 AND INS.aac_code = AAC.AAC_CODE AND INS.CLA_CODE = CLA.CLA_CODE AND INS.PRO_CODE = PRO.PRO_CODE AND CLA.FIL_CODE = FIL.FIL_CODE AND CLA.NIV_CODE= NIV.NIV_CODE", nativeQuery=true)
    Optional<StudentInscriptionDetailsProjection> getStudentInscriptionDetails(Integer code);

}
