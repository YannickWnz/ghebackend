package GHEBACKEND.GHEBACKEND.controller.DonneesReferentielles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.EmploiModel;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.EmploiService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmploiController {

    @Autowired
    private EmploiService emploiService;

    // @GetMapping("/api/emploi")

    @PostMapping("/api/emploi")
    public ResponseEntity<String> addNewEmploi(@RequestBody EmploiModel emploiModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

         
            if(!UtilityMethods.validateInputString(emploiModel.getEmpLib(), 2, 100)) {
                return new ResponseEntity<>("Invalid Emploi Lib format", HttpStatus.BAD_REQUEST);
            }

            emploiService.addNewEmploi(emploiModel);

            return ResponseEntity.ok("Emploi succesfully created");
            // return ResponseEntity.ok("workiing");
            
        } catch (Exception e) {
            logger.error("Error while creating emploi: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating emploi.");
        }
    }

    

}
