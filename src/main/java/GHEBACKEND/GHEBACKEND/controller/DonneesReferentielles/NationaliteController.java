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

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.NationaliteModel;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.NationaliteService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NationaliteController {

    @Autowired
    private NationaliteService nationaliteService;
    
    @Autowired
    private UtilityMethods utilityMethods;

    @GetMapping("/api/nationalite/totalCount")
    public Integer getTotalDataNumber() {
        return nationaliteService.getTotalDataNumber("T_NATIONALITE");
    }

    @GetMapping("/api/nationalite")
    public List<NationaliteModel> getAllNationalite() {
        return nationaliteService.getAllNationaliteData();
    }

    @PostMapping("/api/nationalite")
    public ResponseEntity<String> addNewNationalite(@RequestBody NationaliteModel nationaliteModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            if(!UtilityMethods.validateInputString(nationaliteModel.getNatLib(), 4, 100)) {
                return new ResponseEntity<>("Invalid Lib format", HttpStatus.BAD_REQUEST);
            }

            nationaliteService.addNewNationaliteData(nationaliteModel);

            return ResponseEntity.ok("Nationalite successfully created");

        } catch (Exception e) {

            logger.error("Error while creating nationalite: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating nationalite data.");

        }

    }

    @PutMapping("/api/nationalite/{natCode}")
    public ResponseEntity<String> updateNationaliteData(@PathVariable Integer natCode, @RequestBody NationaliteModel nationaliteModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            nationaliteService.updateNationaliteData(natCode, nationaliteModel);
    
            return ResponseEntity.ok("Data successfully updated");

        } catch (Exception e) {

            logger.error("Error updating data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating matiere data.");
        }

    }

    @DeleteMapping("/api/nationalite/{natCode}")
    public ResponseEntity<String> deleteNationaliteData(@PathVariable Integer natCode) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            nationaliteService.deleteNationaliteData(natCode);
    
            return ResponseEntity.ok("Data successfully deleted");

        } catch (Exception e) {

            logger.error("Error deleting data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting matiere data.");
        }

    }

}
