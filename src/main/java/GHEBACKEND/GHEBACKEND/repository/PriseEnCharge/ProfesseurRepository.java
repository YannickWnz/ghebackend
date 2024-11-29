package GHEBACKEND.GHEBACKEND.repository.PriseEnCharge;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.ProfesseurModel;

@Repository
public interface ProfesseurRepository extends JpaRepository<ProfesseurModel,String>{
    boolean existsByProNomAndProPrenom(String nom,String prenom);
}
