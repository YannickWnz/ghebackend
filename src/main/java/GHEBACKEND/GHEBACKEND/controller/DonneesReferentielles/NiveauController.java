package GHEBACKEND.GHEBACKEND.controller.DonneesReferentielles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Niveau;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.NiveauService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NiveauController {

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private NiveauService niveauService;

    @PostMapping("/api/niveau")
    public ResponseEntity<String> addNewNiveau(@RequestBody Niveau niveau) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            if(!UtilityMethods.validateInputString(niveau.getNivLib(), 4, 100)) {
                return new ResponseEntity<>("Invalid Lib format", HttpStatus.BAD_REQUEST);
            }

            niveauService.addNewNiveau(niveau);
    
            return ResponseEntity.ok("New niveau successfully added");
        } catch (Exception e) {
            logger.error("Error while creating niveau: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating niveau data.");
        }


    }

}
