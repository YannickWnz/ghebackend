package GHEBACKEND.GHEBACKEND.repository.PriseEnCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import java.util.List;


@Repository
public interface PersonnelRepository extends JpaRepository<PersonnelModel,Integer> {

    @Query("select max(p.perCode) from PersonnelModel p")
    Integer findMaxPerCode();

    boolean existsByPerNomAndPerPrenom(String perNom,String perPrenom);
}
