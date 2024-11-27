package GHEBACKEND.GHEBACKEND.repository.PriseEnCharge;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;

@Repository
public interface PersonnelRepository extends JpaRepository<PersonnelModel,Integer> {

    @Query("select max(p.perCode) from PersonnelModel p")
    Integer findMaxPerCode();
    
}
