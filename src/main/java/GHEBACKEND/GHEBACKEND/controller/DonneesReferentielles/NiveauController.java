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

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Niveau;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.NiveauService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NiveauController {

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private NiveauService niveauService;

    @GetMapping("/api/niveau/totalCount")
    public Integer getTotalDataNumber() {
        return niveauService.getTotalDataNumber("T_NIVEAU");
    }

    // method qui se charge de la recuperation de tous les niveaux
    @GetMapping("/api/niveau")
    public List<Niveau> getAllNiveau() {

        return niveauService.getAllNiveau();
    }

    // method qui se charge de la creation de nouveaux niveaux
    @PostMapping("/api/niveau")
    public ResponseEntity<String> addNewNiveau(@RequestBody Niveau niveau) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            // validation du libelle en utilisant la methode definie dans la classe utilityMethods
            if(!UtilityMethods.validateInputString(niveau.getNivLib(), 4, 100)) {
                return new ResponseEntity<>("Invalid Lib format", HttpStatus.BAD_REQUEST);
            }

            // run service function addNewNiveau if no error from validation
            niveauService.addNewNiveau(niveau);
    
            // return Response with statusCode 200
            return ResponseEntity.ok("New niveau successfully added");

        } catch (Exception e) {
            logger.error("Error while creating niveau: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating niveau data.");
        }

    }

    // method qui se charge de la mise a jour des niveaux
    @PutMapping("/api/niveau/{nivCode}")
    public ResponseEntity<String> updateNiveau(@PathVariable int nivCode, @RequestBody Niveau niveau) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            // validation en utilisant la methode definie dans la classe utilityMethods
            if(!UtilityMethods.validateInputString(niveau.getNivLib(), 4, 100) || !UtilityMethods.validateInputString(niveau.getNivModifierPar(), 4, 100)) {
                return new ResponseEntity<>("Invalid inputs format", HttpStatus.BAD_REQUEST);
            }

            // run service function updateNiveauData if no error from validation
            niveauService.updateNiveauData(nivCode, niveau);

            return ResponseEntity.ok("Niveau successfully updated");

        } catch (Exception e) {

            logger.error("Error while updating data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured");        
        }
    
    }

    // method qui se charge de la mise a jour des niveaux
    @DeleteMapping("/api/niveau/{nivCode}")
    public ResponseEntity<String> deleteNiveau(@PathVariable int nivCode) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            niveauService.deleteNiveauData(nivCode);

            return ResponseEntity.ok("Niveau successfully deleted");

        } catch (Exception e) {

            logger.error("Error while deleting data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured");        
        }

        
    }


}
