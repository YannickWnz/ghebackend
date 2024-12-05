package GHEBACKEND.GHEBACKEND.repository.PriseEnCharge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;

@Repository
public interface EtudiantRepo extends JpaRepository<EtudiantModel, Integer> {}
