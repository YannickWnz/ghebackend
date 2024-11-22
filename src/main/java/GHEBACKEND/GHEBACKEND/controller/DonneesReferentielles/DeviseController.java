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

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.DeviseModel;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.DeviseService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin("http://localhost:3000")
@RestController
public class DeviseController {

    @Autowired
    private DeviseService deviseService;

    @Autowired
    private UtilityMethods utilityMethods;

    
    @GetMapping("/api/devise/totalCount")
    public Integer getTotalDataNumber() {
        return deviseService.getTotalNumberOfData("T_DEVISE");
    }

    @GetMapping("/api/devise")
    public ResponseEntity<List<DeviseModel>> getAllDevise() {
        return new ResponseEntity<>(deviseService.getAllDevise(), HttpStatus.OK);
    }

     @PostMapping("/api/devise")
    public ResponseEntity<?> addNewDevise(@RequestBody DeviseModel deviseModel) {
        
        deviseService.addNewDevise(deviseModel);
        
        return ResponseEntity.ok("Devise successfully created");
    }

        
    @PutMapping("/api/devise/{devCode}")
    public ResponseEntity<?> updateAnneeAcademique(@PathVariable int devCode, @RequestBody DeviseModel deviseModel) {
        
        try {

            deviseService.updateDevise(
                devCode, 
                deviseModel.getDevLib(), 
                deviseModel.getDevModifierPar(), 
                deviseModel.getDevStatus()
            );

            return ResponseEntity.ok("Data successfully updated");


        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/api/devise/{code}")
    public ResponseEntity<String> deleteDeviseData(@PathVariable Integer code) {
    
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Code format", HttpStatus.BAD_REQUEST);
            }
            
            deviseService.deleteDeviseData(code);

            return ResponseEntity.ok("Data succesfully deleted");


        } catch (Exception e) {
            
            logger.error("Error while deleting data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting data.");
        
        }

    }


}
