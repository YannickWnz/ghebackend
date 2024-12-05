package GHEBACKEND.GHEBACKEND.repository.Versement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.Versement.Versement;

@Repository
public interface VersementRepository extends JpaRepository<Versement,String> {}
