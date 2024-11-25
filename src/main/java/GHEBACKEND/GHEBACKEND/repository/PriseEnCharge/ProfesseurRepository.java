package GHEBACKEND.GHEBACKEND.repository.PriseEnCharge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.ProfesseurModel;

@Repository
public interface ProfesseurRepository extends JpaRepository<ProfesseurModel,String>{}
