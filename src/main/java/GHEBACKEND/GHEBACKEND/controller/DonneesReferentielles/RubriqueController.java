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

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.RubriqueService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RubriqueController {

    @Autowired
    private RubriqueService rubriqueService;

    @GetMapping("/api/rubrique/totalCount")
    public Integer getTotalDataNumber() {
        return rubriqueService.getTotalDataNumber("T_RUBRIQUE");
    }

    @GetMapping("/api/rubrique")
    public List<RubriqueModel> getAllRubrique() {

        return rubriqueService.getAllRubrique();
    }

    @PostMapping("/api/rubrique")
    public ResponseEntity<String> addNewRubrique(@RequestBody RubriqueModel rubriqueModel) {
        
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            // validation du libelle en utilisant la methode definie dans la classe utilityMethods
            if(!UtilityMethods.validateInputString(rubriqueModel.getRubLib(), 4, 100)) {
                return new ResponseEntity<>("Invalid Lib format", HttpStatus.BAD_REQUEST);
            }

            // run service function addNewNiveau if no error from validation
            rubriqueService.addNewRubrique(rubriqueModel);
    
            // return Response with statusCode 200
            return ResponseEntity.ok("New rubrique successfully added");

        } catch (Exception e) {
            logger.error("Error while creating niveau: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating niveau data.");
        }

    }

    @PutMapping("/api/rubrique/fraisUnique/{code}")
    public ResponseEntity<String> updateRubriqueFraisUniqueData(@PathVariable Integer code, @RequestBody RubriqueModel rubriqueModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            // run service function addNewNiveau if no error from validation
            rubriqueService.updateRubFraisUnique(code, rubriqueModel);
    
            // return Response with statusCode 200
            return ResponseEntity.ok("Rubrique Frais Unique data successfully updated");

        } catch (Exception e) {
            logger.error("Error while updating rubrique: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating rubrique data.");
        }


    }

    @PutMapping("/api/rubrique/{code}")
    public ResponseEntity<String> updateRubriqueData(@PathVariable Integer code, @RequestBody RubriqueModel rubriqueModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            // run service function addNewNiveau if no error from validation
            rubriqueService.updateRubriqueData(code, rubriqueModel);
    
            // return Response with statusCode 200
            return ResponseEntity.ok("Rubrique data successfully updated");

        } catch (Exception e) {
            logger.error("Error while updating rubrique: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating rubrique data.");
        }


    }

    @DeleteMapping("/api/rubrique/{rubCode}")
    public ResponseEntity<String> deleteRubrique(@PathVariable Integer rubCode) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            rubriqueService.deleteRubriqueData(rubCode);

            return ResponseEntity.ok("Rubrique successfully deleted");

        } catch (Exception e) {
            logger.error("Error while deleting rubrique: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting rubrique.");
        }

    }



}
