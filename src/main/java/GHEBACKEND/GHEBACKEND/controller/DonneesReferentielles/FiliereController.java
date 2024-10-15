package GHEBACKEND.GHEBACKEND.controller.DonneesReferentielles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Filiere;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.FiliereService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FiliereController {
    
    @Autowired 
    private FiliereService filiereService;

    @Autowired 
    private UtilityMethods utilityMethods;

    // @PostMapping
    @PostMapping("/api/filiere")
    public ResponseEntity<?> addNewFiliere(@RequestBody Filiere filiere) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            utilityMethods.addDonneesRef(filiere.getFilLib(), filiere.getFilCreerPar(), "FIL_CODE", "T_FILIERE");

            return ResponseEntity.ok("Filiere successfully created");

        } catch (Exception e) {
            logger.error("Error while creating Filiere: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating Filiere.");
        }

    }




}
