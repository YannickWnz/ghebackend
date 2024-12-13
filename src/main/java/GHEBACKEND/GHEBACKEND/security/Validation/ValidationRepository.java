package GHEBACKEND.GHEBACKEND.security.Validation;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<Validation,Integer>{
    Optional<Validation> findByCode(String code);
    void deleteAllByExpirationBefore(Instant now);
}
