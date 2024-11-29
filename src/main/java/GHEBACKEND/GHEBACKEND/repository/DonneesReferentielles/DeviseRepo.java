package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.DeviseModel;

@Repository
public interface DeviseRepo extends JpaRepository<DeviseModel, Integer> {

    
    @Query(value="SELECT DEV_LIB FROM T_DEVISE WHERE DEV_STATUS = 1", nativeQuery=true)
    String getActiveDevise();

}
