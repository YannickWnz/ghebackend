package GHEBACKEND.GHEBACKEND.controller.DonneesReferentielles;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Filiere;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.FiliereService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FiliereController {
    
    @Autowired 
    private FiliereService filiereService;

    @Autowired 
    private UtilityMethods utilityMethods;

    @GetMapping("/api/filiere/totalCount")
    public Integer getTotalDataNumber() {
        return filiereService.getTotalDataNumber("T_FILIERE");
    }

    // creating filiere
    @PostMapping("/api/filiere")
    public ResponseEntity<?> addNewFiliere(@RequestBody Filiere filiere) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            filiereService.addNewFiliereData(filiere);

            return ResponseEntity.ok("Filiere successfully created");

        } catch (Exception e) {
            logger.error("Error while creating Filiere: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating Filiere.");
        }

    }

    // get all filiere method
    @GetMapping("/api/filiere")
    public List<Filiere> getAllFiliere() {

        return filiereService.getAlFiliere();
    }

    // update method
    @PutMapping("/api/filiere/{filCode}")
    public ResponseEntity<?> updateFiliere(@PathVariable int filCode, @RequestBody Filiere filiere) {

        filiereService.updatePromoData(filCode, filiere.getFilLib(), filiere.getFilModifierPar());

        return ResponseEntity.ok("Data successfully updated");

    }

    // delete method
    @DeleteMapping("/api/filiere/{filCode}")
    public ResponseEntity<?> deleteFiliere(@PathVariable int filCode) {

        try {

            utilityMethods.deleteDonneesRef(filCode, "T_FILIERE", "FIL_CODE");

            return ResponseEntity.ok("Filiere deleted successfully");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    
    

}
