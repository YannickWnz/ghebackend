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

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.TypeProfesseur;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.TypeProfesseurService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TypeProfesseurController {

    @Autowired
    private TypeProfesseurService typeProfesseurService;

    
    @GetMapping("/api/typeProfesseur/totalCount")
    public Integer getTotalDataNumber() {
        return typeProfesseurService.getTotalDataNumber("T_TYPE_PROFESSEUR");
    }

    @GetMapping("/api/typeProfesseur")
    public List<TypeProfesseur> getAllTypeProfesseurs() {

        return typeProfesseurService.getAllTypeProf();
    }


    @PostMapping("/api/typeProfesseur") 
    public ResponseEntity<String> addNewTypeProfesseur(@RequestBody TypeProfesseur typeProfesseur) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            // validation du libelle en utilisant la methode definie dans la classe utilityMethods
            if(!UtilityMethods.validateInputString(typeProfesseur.getTprLib(), 4, 100)) {
                return new ResponseEntity<>("Invalid Lib format", HttpStatus.BAD_REQUEST);
            }

            // run service function addNewNiveau if no error from validation
            typeProfesseurService.addNewTypeProfesseur(typeProfesseur);
    
            // return Response with statusCode 200
            return ResponseEntity.ok("New type professeur successfully added");

        } catch (Exception e) {
            logger.error("Error while creating type professeur: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating type professeur data.");
        }

    }

    @PutMapping("/api/typeProfesseur/{code}")
    public ResponseEntity<String> updateTypeProfesseurData(@PathVariable Integer code, @RequestBody TypeProfesseur typeProfesseur) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            // run service function addNewNiveau if no error from validation
            typeProfesseurService.updateTypeProfesseurData(code, typeProfesseur);
    
            // return Response with statusCode 200
            return ResponseEntity.ok("Data successfully updated");

        } catch (Exception e) {
            logger.error("Error while updating type professeur: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating type professeur data.");
        }


    }

    
    @DeleteMapping("/api/typeProfesseur/{code}")
    public ResponseEntity<String> deleteRubrique(@PathVariable Integer code) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            typeProfesseurService.deleteTypeProfesseurData(code);

            return ResponseEntity.ok("Rubrique successfully deleted");

        } catch (Exception e) {
            logger.error("Error while deleting data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting data.");
        }

    }





}
