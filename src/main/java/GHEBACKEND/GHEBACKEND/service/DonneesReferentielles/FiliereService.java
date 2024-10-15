package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.FiliereRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepo filiereRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UtilityMethods utilityMethods;



}
