package GHEBACKEND.GHEBACKEND.security.Jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface JwtRepository extends JpaRepository<Jwt,Integer>{
    
    Optional<Jwt> findByValueAndDesactiveAndExpire(String value, boolean desactive, boolean expire);

    @Query("select max(j.id) from Jwt j")
    Integer findMaximumJwt();
}
