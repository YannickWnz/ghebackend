package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ServiceModel;


@Repository
public interface ServiceRepo extends JpaRepository<ServiceModel, Integer> {

    @Query(value = "SELECT * FROM T_SERVICE WHERE DIR_CODE = ?1", nativeQuery=true)
    List<ServiceModel> getServiceInSelectedDirection(Integer code);
}
