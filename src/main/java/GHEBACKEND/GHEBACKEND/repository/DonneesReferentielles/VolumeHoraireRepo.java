package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseMatiereProjection;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.VolumeHoraire;

@Repository
public interface VolumeHoraireRepo extends JpaRepository<VolumeHoraire, Integer> {

    @Query(value="SELECT * FROM T_CLASSE_MATIERE WHERE CLA_CODE = ?1 AND MAT_CODE = ?2", nativeQuery=true)
    List<VolumeHoraire> getAssignedVolumeHoraire(Integer claCode, Integer matCode);

    @Query(value="SELECT CLM_CODE, CLM_VOLUME_HORAIRE, CLA_LIB, MAT_LIB FROM T_CLASSE_MATIERE CLM, T_CLASSE CLA, T_MATIERE MAT WHERE CLM.CLA_CODE = CLA.CLA_CODE AND CLM.MAT_CODE = MAT.MAT_CODE", nativeQuery=true)
    List<ClasseMatiereProjection> getVolumeHoraireData();

}
