package GHEBACKEND.GHEBACKEND.repository.Versement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;

@Repository
public interface RubriquePayerRepository extends JpaRepository<RubriquePayer, String>{}
