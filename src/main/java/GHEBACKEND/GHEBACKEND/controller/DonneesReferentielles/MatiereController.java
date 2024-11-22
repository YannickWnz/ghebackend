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

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.MatiereModel;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.MatiereService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private UtilityMethods utilityMethods;

    @GetMapping("/api/matiere/totalCount")
    public Integer getTotalDataNumber() {
        return matiereService.getTotalDataNumber("T_MATIERE");
    }

    @GetMapping("/api/matiere")
    public List<MatiereModel> getAllMatiere() {
        return matiereService.getAllMatiere();
    }

    @PostMapping("/api/matiere")
    public ResponseEntity<String> addNewMatiere(@RequestBody MatiereModel matiereData) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            // if(!UtilityMethods.validateInputString(matiereData.getMatLib(), 4, 100)) {
            //     return new ResponseEntity<>("Invalid Lib format", HttpStatus.BAD_REQUEST);
            // }

            matiereService.addNewMatiere(matiereData);

            return ResponseEntity.ok("Matiere successfully created");

        } catch (Exception e) {

            logger.error("Error while creating matiere: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating matiere data.");

        }

    }

    @PutMapping("/api/matiere/{matCode}")
    public ResponseEntity<String> updateMatiereData(@PathVariable Integer matCode, @RequestBody MatiereModel matiereModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            matiereService.updateMatiere(matCode, matiereModel);
    
            return ResponseEntity.ok("Data successfully updated");

        } catch (Exception e) {

            logger.error("Error updating data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating matiere data.");
        }

    }

    @DeleteMapping("/api/matiere/{matCode}")
    public ResponseEntity<String> deleteMatiereData(@PathVariable Integer matCode) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            matiereService.deleteMatiereData(matCode);
    
            return ResponseEntity.ok("Data successfully deleted");

        } catch (Exception e) {

            logger.error("Error deleting data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting matiere data.");
        }

    }

}
