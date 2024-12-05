package GHEBACKEND.GHEBACKEND.repository.PriseEnCharge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonneContactModel;

@Repository
public interface PersonneContactRepo extends JpaRepository<PersonneContactModel, Integer> {}
