package GHEBACKEND.GHEBACKEND.controller.DonneesReferentielles;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.DirectionModel;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.DirectionService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@RestController
public class DirectionController {

    @Autowired
    private DirectionService directionService;

    @Autowired
    private UtilityMethods utilityMethods;

    @GetMapping("/api/direction/totalCount")
    public Integer getDirectionDataTotalCount() {
        return directionService.getTotalDataNumber("T_DIRECTION");
    }

    @GetMapping("/api/direction")
    public List<DirectionModel> getAllDirection() {
        return directionService.getAllDirectionData();
    }

    @PostMapping("/api/direction")
    public ResponseEntity<String> addNewDirectionData(@RequestBody DirectionModel directionModel) {
    
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            if(!UtilityMethods.validateInputString(directionModel.getDirLib(), 2, 100)) {
                return new ResponseEntity<>("Invalid Direction Lib format", HttpStatus.BAD_REQUEST);
            }
            
            directionService.addNewDirectionData(directionModel);

            return ResponseEntity.ok("Direction succesfully created");


        } catch (Exception e) {
            
            logger.error("Error while creating direction: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating direction.");
        
        }

    }

    @PutMapping("/api/direction/{code}")
    public ResponseEntity<String> updateNewDirectionData(@PathVariable Integer code, @RequestBody DirectionModel directionModel) {
    
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Direction Code format", HttpStatus.BAD_REQUEST);
            }

            if(!UtilityMethods.validateInputString(directionModel.getDirLib(), 2, 100)) {
                return new ResponseEntity<>("Invalid Direction Lib format", HttpStatus.BAD_REQUEST);
            }
            
            directionService.updateDirectionData(code, directionModel);

            return ResponseEntity.ok("Direction succesfully updated");


        } catch (Exception e) {
            
            logger.error("Error while updating direction: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating direction.");
        
        }

    }

    @DeleteMapping("/api/direction/{code}")
    public ResponseEntity<String> deleteNewDirectionData(@PathVariable Integer code) {
    
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Direction Code format", HttpStatus.BAD_REQUEST);
            }
            
            directionService.deleteDirectionData(code);

            return ResponseEntity.ok("Direction succesfully deleted");


        } catch (Exception e) {
            
            logger.error("Error while deleting direction: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting direction.");
        
        }

    }

}
