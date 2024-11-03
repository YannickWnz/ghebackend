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

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.EmploiModel;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.EmploiService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmploiController {

    @Autowired
    private EmploiService emploiService;

    @GetMapping("/api/emploi/totalCount") 
    public Integer getEmploiTotalCount() {
        return emploiService.getTotalDataNumber("T_EMPLOI");
    }

    @GetMapping("/api/emploi")
    public List<EmploiModel> getAllEmploiData() {
        return emploiService.getAllEmploiData();
    }

    @PostMapping("/api/emploi")
    public ResponseEntity<String> addNewEmploi(@RequestBody EmploiModel emploiModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

         
            if(!UtilityMethods.validateInputString(emploiModel.getEmpLib(), 2, 100)) {
                return new ResponseEntity<>("Invalid Emploi Lib format", HttpStatus.BAD_REQUEST);
            }

            emploiService.addNewEmploi(emploiModel);

            return ResponseEntity.ok("Emploi succesfully created");
            
        } catch (Exception e) {
            logger.error("Error while creating emploi: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating emploi.");
        }
    }

    @PutMapping("/api/emploi/{code}")
    public ResponseEntity<String> updateEmploiData(@PathVariable Integer code, @RequestBody EmploiModel emploiModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
         
            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Emploi Code format", HttpStatus.BAD_REQUEST);
            }

            if(!UtilityMethods.validateInputString(emploiModel.getEmpLib(), 2, 100)) {
                return new ResponseEntity<>("Invalid Emploi Lib format", HttpStatus.BAD_REQUEST);
            }

            emploiService.editEmploiData(code, emploiModel);

            return ResponseEntity.ok("Emploi succesfully updated");
            
        } catch (Exception e) {
            logger.error("Error while updated emploi: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updated emploi.");
        }

    }

    @DeleteMapping("/api/emploi/{code}")
    public ResponseEntity<String> deleteEmploiData(@PathVariable Integer code) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Emploi Code format", HttpStatus.BAD_REQUEST);
            }
            
            emploiService.deleteEmploiData(code);

            return ResponseEntity.ok("Emploi succesfully deleted");
            
        } catch (Exception e) {
            logger.error("Error while deleting emploi data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleted emploi data.");
        }

    }

    

}
